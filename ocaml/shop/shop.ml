open Str

(* missing in OCaml *)
let rec range i j = if i > j then [] else i :: (range (i+1) j)


(* get integers from a string *)
let split_ints str = 
  List.map int_of_string (Str.split (Str.regexp " ") str) 


let read_test() =
  let [n;k] =  split_ints (read_line()) in
  let options = List.map (fun x -> let [c;w] = split_ints (read_line()) in (c,w)) (range 1 n) in
  (k,options)


let compute = fun (k,options) ->
  let rec backtrack k min choices sum =
    if (k < min) || ((List.length choices) = 0)  then sum
    else
      let options = List.filter (fun (cost,weight) -> k > cost) choices in
      let apply_choice = fun (cost,weight) ->
        (backtrack (k - cost) min options (sum + weight))
      in
      List.fold_left Pervasives.max 0 (List.map apply_choice options)
  in
  backtrack k (List.fold_left Pervasives.min 100000000 (List.map (fun (c,w) -> c) options)) options 0


let main() =
  let testcases = read_int() in
  let results = List.map (fun x -> (compute (read_test()))) (range 1 testcases) in
  List.iter (fun x -> print_int x; print_newline()) results;
  exit 0;;

main();;

  
