open Str

(* missing in OCaml *)
let rec range i j = if i > j then [] else i :: (range (i+1) j)


(* get integers from a string *)
let split_ints str = 
  List.map int_of_string (Str.split (Str.regexp " ") str) 


let read_test() =
  let n = read_int() in
  let moves = split_ints (read_line()) 
  and start = Array.of_list (range 1 n) in
  (moves,start)


let compute = fun (moves,start) ->
  let rec swap pos = function
    | 0 -> ()
    | _ when (pos = 0) -> ()
    | count -> let r = (Array.get start pos)
      and l = (Array.get start (pos-1)) in
      (Array.set start pos l); 
      (Array.set start (pos-1) r);
      swap (pos-1) (count-1)
  in
  let rec move index = function
    | [] -> (Array.to_list start)
    | 0 :: xs -> (move (index + 1) xs)
    | x :: xs -> (swap index x); (move (index + 1) xs)
  in
  (move 0 moves)


let print_list ls =
  let ils = List.map string_of_int ls in
  print_string (String.concat " " ils)


let main() =
  let testcases = read_int() in
  let results = List.map (fun x -> (compute (read_test()))) (range 1 testcases) in
  List.iter (fun ls -> print_list ls; print_newline()) results;
  exit 0;;

main();;

  
