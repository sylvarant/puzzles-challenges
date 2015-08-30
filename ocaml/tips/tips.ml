open Str

(* missing in OCaml *)
let rec range i j = if i > j then [] else i :: (range (i+1) j)

(* get integers from a string *)
let split_ints str = 
  List.map int_of_string (Str.split (Str.regexp " ") str) 

(* compute the maximal possible tip *)
let rec maxtip n x y als bls max =
  if n == 0 then max
  else
    (* decision making *)
    if x == 0 then
      (maxtip (n-1) 0 (y-1) [] (List.tl bls) (max + (List.hd bls)))
    else if y == 0 then
      (maxtip (n-1) (x-1) 0 (List.tl als) [] (max + (List.hd als)))
    else 
      let a = List.hd als 
      and b = List.hd bls in
      let left = (maxtip (n-1) (x-1) y (List.tl als) (List.tl bls) (max + a))
      and right = (maxtip (n-1) x (y-1) (List.tl als) (List.tl bls) (max + b))
      in (Pervasives.max left right)
    

let main() =
  let nxy   = split_ints (read_line()) in
  let alist = split_ints (read_line()) in
  let blist = split_ints (read_line()) in
  print_int (maxtip (List.hd nxy) (List.nth nxy 1) (List.nth nxy 2) alist blist 0);
  print_newline();
  exit 0;;

main();;

  
