(* Transitive closure in Ocaml *)

(* check edges for match *)
let rec edgecheck e t x = match e with
	| [] -> ([],t)
	| (a,b) :: es -> 
		if not (List.exists (fun p -> p = a) t) then 
			(edgecheck es t x)	
		else if not (List.exists (fun p ->  p = b) t) then
			let lt = b::t in
			let (r,tt) = (edgecheck es lt x) in (((x,b)::r),tt)
	    else if b == x then
			let (r,tt) = (edgecheck es t x) in (((x,x)::r),tt)
		else
			(edgecheck es t x)
			
(* grow until no more change *)
let rec grow o t e x =
	if o == (List.length t) then
		[]
	else
		let (r,tt) = (edgecheck e t x) in r @ (grow (List.length t) tt e x)

(* compute closure *)
let rec transitive v e = match v with
	| [] -> []
	| x :: vs ->
		let r = (grow 0 [x] e x) in r @ (transitive vs e)

(* main function *)
let main() =
	let e = [('A','B'),('B','C'),('D','E')] in
	let v = ['A','B','C','D','E'] in
	let r = (transitive v e) in
	for i = 0 to r.length do 
		let (a,b) = r.(i) in printf "%c->%c" a b
	done;
	exit 0;;

main;;
