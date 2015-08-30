open Str

type query = Q of int | U of int * int

(* missing in OCaml *)
let rec range i j = if i > j then [] else i :: (range (i+1) j)

(* get integers from a string *)
let split_ints str = 
  List.map int_of_string (Str.split (Str.regexp " ") str) 

let to_tuple str =
  let ls = (Str.split (Str.regexp " ") str) in
  (int_of_string (List.hd ls),int_of_string (List.hd (List.tl ls)))

let to_query str =
  let ls = (Str.split (Str.regexp " ") str) in
  match (List.hd ls).[0] with
  | 'Q' -> Q (int_of_string (List.hd (List.tl ls)))
  | 'U' -> U(int_of_string (List.nth ls 1),int_of_string (List.nth ls 2))
  | _ -> raise (Failure "incorrect input")

let build_commanders rels =
  if (List.length rels) == 0 then []
  else
    let compt = fun (a,b) (x,y) -> (compare a x) in
    let sortedr =  List.sort compt rels in
    (* the magic *)
    let rec build current underlings = function
    | [] -> [(current,underlings)]
    | (a,b) :: xs -> if a == current
      then (build a (b::underlings) xs)
      else (current,underlings) :: (build a [b] xs)
    in
    (build (fst (List.hd sortedr)) [] sortedr)

let rec strength commanders soldp s =
  let my_strength = (Array.get !soldp (s-1)) in
  try 
    let (_,underlings) = (List.find (fun (a,b) -> a == s) commanders) in
    (List.fold_left (fun x y -> x + y) my_strength (List.map (strength commanders soldp) underlings))
  with _ -> my_strength


let do_query commanders soldp = function
  | U (s,x) -> (Array.set !soldp (s-1) x)
  | Q s -> print_int (strength commanders soldp s); print_newline()


let main() =
  let nm   = split_ints (read_line()) in
  let sold = Array.of_list (split_ints (read_line())) in
  let rels = List.map to_tuple (List.map (fun x -> read_line()) (range 1 ((List.hd nm) -1))) in
  let quer = List.map to_query (List.map (fun x -> read_line()) (range 1 (List.hd (List.tl nm)))) in
  let commanders = build_commanders rels in
  let soldiers = ref sold in
  List.iter (fun x -> (do_query commanders soldiers x)) quer;
  exit 0;;

main();;


