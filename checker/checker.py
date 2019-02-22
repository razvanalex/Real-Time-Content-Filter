
import os
from subprocess import call

comp_error = 0.011
max_diff_lines_print = 5

def compare_with_error(valueA, valueB, error):
	valueA = abs(valueA)
	valueB = abs(valueB)
	
	if max(valueA, valueB) - min(valueA, valueB) <= error:
		return True;
	
	return False;
	

def compare_values(outLine, refLine):
	out_line_tokens = outLine.split(' ')
	ref_line_tokens = refLine.split(' ')
	
	for i in range(len(out_line_tokens)):
		if i == 3 or i == 4:
			valueA = float(out_line_tokens[i].split('%')[0]);
			valueB = float(ref_line_tokens[i].split('%')[0]);
			
			if compare_with_error(valueA, valueB, comp_error) == False:
				return False
		else:
			if out_line_tokens[i] != ref_line_tokens[i]:
				return False
	
	return True

def fileCompare(out_file, ref_file):
	out_file_lines = []
	ref_file_lines = []
	
	error = ""
	
	with open(out_file) as f:
		out_file_lines = f.read().splitlines()

	with open(ref_file) as f:
		ref_file_lines = f.read().splitlines()

	if len(out_file_lines) != len(ref_file_lines):
		error = error + "\n\tDifferent number of lines between out and ref files. No compare!"
		return error
	
	diff_lines_count = 0
	
	for i in range(len(out_file_lines)):
		if compare_values(out_file_lines[i], ref_file_lines[i]) == False:
			error = error +  "\n\tDifference on line " + str(i + 1)
			diff_lines_count = diff_lines_count + 1
		
		if diff_lines_count > max_diff_lines_print:
			error = error + "\n\tMore than " + str(max_diff_lines_print) + " different lines! Stop compare!"
			return  error
	
	return error

def check_readme():

    if not os.path.exists("Readme") and\
       not os.path.exists("README") and\
       not os.path.exists("Readme.txt") and\
       not os.path.exists("README.txt"):
        print "No README file in the archive!"
        print "Explanations in the readme file amount to 10% of the assignment grade!"


def check_makefile():

    if not os.path.exists("Makefile") and \
       not os.path.exists("makefile"):
        print "Using default makefile"
        cmd = "cp Makefile-default Makefile"
        call([cmd], shell=True)


def check_language():
    for root, dirs, files in os.walk("."):
        for name in files:
            if len(name) > 5 and \
               name[-5:] == ".java":
                check_makefile()
                return "java"
            if name != "checker.py" and name[-3:] == ".py":
                return "py"
                
    return "c++"


def build_tracker():
    print "Building tracker"
    cmd = "make -f Makefile-tracker build-tracker"
    call([cmd], shell=True)

def install_java():
    cmd = "bash java_install.sh"
    call([cmd], shell=True)

def clean_tracker():

    cmd = "make -f Makefile-tracker clean"
    call([cmd], shell=True)


def build_solution():
    print "Building solution"
    cmd = "make build"
    call([cmd], shell=True)


def clean_solution():

    cmd = "make clean"
    call([cmd], shell=True)

def cpl_version():

    cmd = "g++ -v"
    call([cmd], shell=True)    

    cmd = "java -version"
    call([cmd], shell=True)

###########################################################

# Assigns each test equal score and and equal timeout
# Result is a list of tuples of the form: (test_idx, test_score, test_timeout)
def gen_tests_equal_score(total_score, num_tests, timeout):
    return zip(xrange(num_tests), [total_score / num_tests] * num_tests, [timeout] * num_tests)


def gen_tests_unequal_score(total_score, num_tests, timeout):

    tests = gen_tests_equal_score(total_score, num_tests, timeout)

    tests[0] = (0, 1, tests[0][2]) 
    tests[1] = (1, 1, tests[0][2])
    for idx in xrange(2, len(tests)):
        tests[idx] = (idx, tests[idx][1] - 1, tests[idx][2])
        
    s = 0
    for elem in tests:
        s += elem[1]

    idx = len(tests) - 1
    while s < total_score:    
        incr = 1
        if (total_score - s) >= 4:
            incr = 4
        elif (total_score - s) >= 3:
            incr = 3
        elif (total_score - s) >= 2:
            incr = 2
        
        tests[idx] = (tests[idx][0], tests[idx][1] + incr, tests[idx][2])        
        s += incr
        idx -= 1
    
    return tests        
        
def default_check_make_run(problem_id):

    makefile = "Makefile"
    if os.path.exists("makefile"):
        makefile = "makefile"

    try:
        f = open(makefile)
        for line in f:
            if "run" in line:
                return True
        f.close()
    except IOError:
        return False

    return False


def default_copy_test(test_idx, name):

    tests_folder = "test/" + name

    # copy test
    copy_input = "cp " + tests_folder + str(test_idx) + ".in " + "in.txt"
    copy_ok    = "cp " + tests_folder + str(test_idx) + ".ok " + "ref.txt"

    # remove old output file
    rm_out = "rm -f " + "out.txt"

    call([copy_input], shell=True)
    call([copy_ok], shell=True)
    call([rm_out], shell=True)


def check_TLE():
    with open("error.time") as f:
        if len(f.read()) > 0:
            return True
    return False


def print_test_result(test_id, score, full_score, reason):
    print("Test {} ......... {}/{}: {}\n".format(test_id, score, full_score, reason))
    # print "-- " + time + "s"


def default_out_ok_names(problem_name):
    out_file = "out.txt"
    ok_file  = "ref.txt"
    return out_file, ok_file

def default_checker(out_file, ok_file, score, ans_type=int):

    # with open("output.time") as f:
    #     time = f.read()

    try:
        f = open(out_file)
        out_ans = ans_type(f.read())
        f.close()
    except Exception:
        return 0, "Invalid output file"

    with open(ok_file) as f:
        ok_ans = ans_type(f.read())

	error = fileCompare(out_file, ok_file)
	
    if len(error) == 0:
        return score, "OK"

    return 0, error


def check_execution_errors(print_stderr=False):

    with open("error.exec") as f:
        errors = f.read().replace("Clock skew detected", "")
        if len(errors) > 0:        
            if print_stderr:
                print errors
            else:
                if "StackOverflowError" in errors:
                    print "StackOverflow detected!"
                elif "Segmentation fault" in errors:
                    print "Segmentation fault detected!"
                elif "Floating point" in errors:
                    print "Floating point exception detected!"                                
            return True
    return False


def default_run(problem_name,
                tests,
                checker=default_checker,
                setup_tests=default_copy_test,
                out_ok_names=default_out_ok_names):

    current_score = 0
    out_file, ok_file = out_ok_names(problem_name)

    for (test_idx, test_score, timeout) in tests:

        setup_tests(test_idx, problem_name)

        # run_cmd = "./tracker run " + str(timeout)
        run_cmd = "make -s run < in.txt > out.txt 2> error.exec"
        call([run_cmd], shell=True)
        # if check_TLE():
        #     print_test_result(test_idx,
        #                       score=0,
        #                       full_score=test_score,
        #                       reason="Time Limited Exceeded",
        #                       time="(>" + str(timeout) + ")")            
        if check_execution_errors():
            print_test_result(test_idx,
                              score=0,
                              full_score=test_score,
                              reason="Execution Errors")
        else:
            score, reason = checker(out_file, ok_file, test_score, ans_type=str)
            print_test_result(test_idx, score, test_score, reason)
            current_score += score

    return current_score


def main():

    # cpl_version()
    #check_readme()
    # build_tracker()
    build_solution()

    total_score  = 100
    coding_style = 0
    readme_make  = 10
    score_problems = (total_score - coding_style - readme_make)

    current_score  = 0

    tests_config = gen_tests_equal_score(score_problems,
                                            num_tests=10,
                                            timeout=10)
    
    current_score += default_run(problem_name="feed-filter",
                                 tests=tests_config,
                                 checker=default_checker)

    print "Total score " + str(current_score) + "/" + str(score_problems)

    clean_solution()
    # clean_tracker()


if __name__ == "__main__":
   main()


