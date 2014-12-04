(* Marshall Bowers *)
(* Dr Richard Wyatt *)
(* 9 December 2014 *)
(* traversals.sml *)

datatype 'a BinaryTree = empty | tree of 'a * 'a BinaryTree * 'a BinaryTree ;

fun preOrder (tree(root, empty, empty)) = [root]
| preOrder (tree(root, empty, right)) = root :: preOrder right
| preOrder (tree(root, left, empty)) = root :: preOrder left
| preOrder (tree(root, left, right)) = [root] @ preOrder left @ preOrder right;

fun inOrder (tree(root, empty, empty)) = [root]
| inOrder (tree(root, empty, right)) = root :: inOrder right
| inOrder (tree(root, left, empty)) = inOrder left @ [root]
| inOrder (tree(root, left, right)) = inOrder left @ [root] @ inOrder right;

fun postOrder (tree(root, empty, empty)) = [root]
| postOrder (tree(root, empty, right)) = postOrder right @ [root]
| postOrder (tree(root, left, empty)) = postOrder left @ [root]
| postOrder (tree(root, left, right)) = postOrder left @ postOrder right @ [root];

(* Print Functions *)

fun printInt n = print(Int.toString n);

fun printReal n = print(Real.toString n);

fun printX A = print "A"
| printX B = print "B"
| printX C = print "C"
| printX D = print "D"
| printX E = print "E"
| printX F = print "F"
| printX G = print "G"
| printX H = print "H";

val indentLevel = 0;
val tab = 2;

fun displayTree (t, printf) =
    let
        fun spaces n = if n = 0 then "" else " " ^ (spaces (n - 1));

        fun helper (tree(root, empty, left), printf, n) = (print((spaces n) ^ "-\n"))
        | helper (tree(root, right, empty), printf, n) = print((spaces n) ^ "-\n")
        | helper (tree(root, right, left), printf, n) = (
            helper(right, printf, (n + 2));
            print(spaces n);
            printf root;
            helper(left, printf, (n + 2))
        );
    in
        helper(t, printf, 0)
    end;
