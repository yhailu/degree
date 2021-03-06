(load (merge-pathnames "homework.lisp" *load-truename*))

(defun tests ()
    "Documentation for tests."
    (test-sum)
    (test-add-1-all)
    (test-my-replace)
    "Tests passed.")

(defun test-sum ()
    (assert (eq (sum 3 4) (+ 3 4))
        ()
        "~S not equal to ~S" (sum 3 4) (+ 3 4))
    (assert (eq (sum 3 -4) (+ 3 -4))
        ()
        "~S not equal to ~S" (sum 3 -4) (+ 3 -4))
    (assert (eq (sum 3 'a) nil)
        ()
        "~S not equal to ~S" (sum 3 'a) nil))

(defun test-add-1-all ()
    (assert (equal (add-1-all '(1 2 3 4 5)) '(2 3 4 5 6))
        ()
        "~S not equal to ~S" (add-1-all '(1 2 3 4 5)) '(2 3 4 5 6))
    (assert (equal (add-1-all '(1 2 3 "q" 5)) nil)
        ()
        "~S not equal to ~S" (add-1-all '(1 2 3 "q" 5)) nil)
    (assert (equal (add-1-all "qwer") nil)
        ()
        "~S not equal to ~S" (add-1-all "qwer") nil))

(defun test-my-replace ()
    (assert (equal (my-replace '(a) '(x) '(a (a) (b (a)))) '(a (x) (b (x))))
        ()
        "~S not equal to ~S" (my-replace '(a) '(x) '(a (a) (b (a)))) '(a (x) (b (x))))
    (assert (equal (my-replace '(a) '(x) '(a ((a) b (a)) b a)) '(a ((x) b (x)) b a))
        ()
        "~S not equal to ~S" (my-replace '(a) '(x) '(a ((a) b (a)) b a)) '(a ((x) b (x)) b a))
    (assert (equal (my-replace 'a '(x y (z)) '(a (a b (a)) b a)) '((x y (z)) ((x y (z)) b ((x y (z)))) b (x y (z))))
        ()
        "~S not equal to ~S" (my-replace 'a '(x y (z)) '(a (a b (a)) b a)) '((x y (z)) ((x y (z)) b ((x y (z)))) b (x y (z)))))

(defun test-fibonacci ())
