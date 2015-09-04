open Str

(* missing in OCaml *)
let rec range i j = if i > j then [] else i :: (range (i+1) j)

(* get integers from a string *)
let split_ints str = 
  List.map int_of_string (Str.split (Str.regexp " ") str) 

let read_test() =
  let _ = read_int() in
  let numbers = split_ints (read_line()) in
  numbers

let sum = function
  | a::b::c::d::[] ->
    (Pervasives.abs (a+b-c-d)) +
    (Pervasives.abs (a+c-b-d)) +
    (Pervasives.abs (a+d-b-c)) +
    (Pervasives.abs (c+d-a-b)) +
    (Pervasives.abs (b+d-a-c)) +
    (Pervasives.abs (b+c-a-d))
  | _ -> raise (Failure "Incorrect combination")

let rec combinations ls = function
  | 0 -> [[]]
  | m -> match ls with
    | [] -> []
    | x::xs -> (List.map (fun y -> x :: y) (combinations xs (m-1))) @ (combinations xs m)

let compute ls = 
  List.fold_left (fun x y -> x + (sum y)) 0 (combinations ls 4)

let main() =
  let testcases = read_int() in
  let results = List.map (fun x -> (compute (read_test()))) (range 1 testcases) in
  List.iter (fun x -> print_int x; print_newline()) results;
  exit 0;;

main();;

  
