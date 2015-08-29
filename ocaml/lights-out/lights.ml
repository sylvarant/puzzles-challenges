
(* missing in OCaml *)
let rec range i j = if i > j then [] else i :: (range (i+1) j)

(* missing in OCaml *)
let rec remove_dups lst= match lst with 
| [] -> []
| h::t -> h::(remove_dups (List.filter (fun x -> x<>h) t))

(* IO :: in *)
let read_board() = 
  let n = read_int() in
  let lines = List.map (fun x -> read_line()) (range 1 n) in
  let to_bool = function
    | '0' -> false
    | '1' -> true
    | _ -> raise (Failure "Incorrect Input")
  in
  let rec to_list str pos = match (n - pos) with
    | 0 -> []
    | _ -> (to_bool (str.[pos])) :: (to_list str (pos+1)) in
  let board = List.map (fun str -> (to_list str 0)) lines in 
  board

(* IO :: out -- OK *)
let print_solution = function
  | None -> print_endline "no solution found"
  | Some ls ->
    print_int (List.length ls);
    print_newline();
    List.iter (fun (r,c) -> print_int r; print_string " "; print_int c;
      print_newline()) (List.rev ls)

(* IO :: debug -- OK *)
let print_board board = 
  let str_row r = String.concat "" (List.map (function true -> "1" | false -> "0") r) in
  List.iter (fun x -> print_endline (str_row x)) board

(* Update the board *)
let press_light n board r c =
  let quadruple = [[(r-1,c)] ; [(r,c-1) ; (r,c); (r,c+1)]; [(r+1,c)] ] in
  let within_bounds = function 
    | (x,y) -> (x >= 1 && x <= n) && (y >= 1 && y <= n)
  in
  let applicable = List.filter (function | [] -> false | _ -> true) 
    (List.map (fun ls -> List.filter within_bounds ls) quadruple) in
  let rec set_row cs row count = match cs with
    | [] -> row
    | x :: xs when x == count -> (not (List.hd row)) :: (set_row xs (List.tl row) (count + 1))
    | _ -> (List.hd row) :: (set_row cs (List.tl row) (count+1))
  in
  let rec set_board rows count = function
    | [] -> rows
    | (x :: xs) when (fst (List.hd x)) == count -> 
      (set_row (List.map (fun (x,y) -> y) x) (List.hd rows) 1) :: (set_board (List.tl rows) (count + 1) xs)
    | _ as y -> (List.hd rows) :: (set_board (List.tl rows) (count + 1) y)
  in
  (set_board board 1 applicable)
  
(* find solution state *)
let rec is_solution = function
  | [] -> true
  | x :: xs -> 
    let rec is_clear = function
      | [] -> true
      | true :: ys -> false
      | false :: ys -> (is_clear ys)
    in
    if (is_clear x) then (is_solution xs) 
    else false

(* compute next possible moves *)
let rec options moves count = function
  | [] -> let within_bounds = function 
    | (x,y) -> (x >= 1 && x <= (count -1)) && (y >= 1 && y <= (count - 1))
    in remove_dups (List.filter within_bounds moves)
  | r :: rs -> let rec cols pos = function
    | [] -> []
    | true :: xs -> (count-1,pos) :: (count,pos) :: (count + 1,pos) :: (count, pos - 1) :: 
      (count, pos + 1) :: (cols (pos + 1) xs)
    | false :: xs -> (cols (pos+1) xs)
    in
    (options ((cols 1 r) @ moves) (count + 1) rs)

let reject = function
  | [] -> false
  | x :: xs -> 
    (List.length (List.filter (fun y -> x <> y) xs)) < (List.length xs)

(* magic *)
let rec solve board moves = 
  if (reject moves) then None
  else if (is_solution board) then (Some moves)
  else 
    let n = (List.length board) in
    let comp = fun (a,b) (x,y) ->
      if a < x then -1
      else if a == x then (compare b y)
        else 1
    in 
    let rec try_options = function
      | [] -> None
      | (x,y) :: xs -> 
        match (solve (press_light n board x y) ((x,y)::moves)) with
        | None -> try_options xs
        | _ as result -> result
    in
    try_options (List.sort comp (options [] 1 board))


(* main function -- OK *)
let main() =
  let ntests = read_int() in
  let boards = List.map (fun x -> read_board()) (range 1 ntests) in
  let solutions = List.map (fun x -> (solve x [])) boards in
  List.iter print_solution solutions;
  exit 0;;

main();;
