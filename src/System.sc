;;; Sierra Script 1.0 - (do not remove this comment)
(script# 999)
(include sci.sh)
(use Main)
(use Intrface)

(public
	sign 0
	umod 1
	Min 2
	Max 3
	InRect 4
	OneOf 5
)

(procedure (sign param1)
	(return (if (< param1 0) -1 else (> param1 0)))
)

(procedure (umod param1 param2)
	(= param1 (- param1 (* param2 (/ param1 param2))))
	(if (< param1 0) (= param1 (+ param1 param2)))
	(return param1)
)

(procedure (Min param1 &tmp temp0)
	(return
		(if
		(or (== argc 1) (< param1 (= temp0 (Min &rest))))
			param1
		else
			temp0
		)
	)
)

(procedure (Max param1 &tmp temp0)
	(return
		(if
		(or (== argc 1) (> param1 (= temp0 (Max &rest))))
			param1
		else
			temp0
		)
	)
)

(procedure (InRect param1 param2 param3 param4 param5 param6)
	(return
		(if
			(if
			(<= param1 (if (< argc 6) (param5 x?) else param5))
				(<= (if (< argc 6) (param5 x?) else param5) param3)
			)
			(if
				(if
				(<= param2 (if (< argc 6) (param5 y?) else param6))
					(<= (if (< argc 6) (param5 y?) else param6) param4)
				)
				1
			)
		else
			0
		)
	)
)

(procedure (OneOf param1 param2 &tmp temp0)
	(= temp0 0)
	(while (< temp0 (- argc 1))
		(if (== param1 [param2 temp0])
			(return (if (or param1 1) 1 else 0))
		)
		(++ temp0)
	)
	(return 0)
)

(class Obj
	(properties)
	
	(method (new)
		(Clone self)
	)
	
	(method (init)
	)
	
	(method (doit)
		(return self)
	)
	
	(method (dispose)
		(DisposeClone self)
	)
	
	(method (showStr param1)
		(StrCpy param1 name)
	)
	
	(method (showSelf &tmp [temp0 200])
		(Print (self showStr: @temp0))
	)
	
	(method (perform param1)
		(param1 doit: self &rest)
	)
	
	(method (respondsTo param1)
		(RespondsTo self param1)
	)
	
	(method (isMemberOf param1)
		(return
			(if
				(and
					(& (param1 -info-?) $8000)
					(not (& -info- $8000))
					(== species (param1 species?))
				)
				1
			else
				0
			)
		)
	)
	
	(method (isKindOf param1 &tmp objSuperClass)
		(= objSuperClass (self superClass?))
		(return
			(if
				(or
					(== species (param1 species?))
					(and
						(IsObject objSuperClass)
						(objSuperClass isKindOf: param1)
					)
				)
				1
			else
				0
			)
		)
	)
	
	(method (yourself)
		(return self)
	)
)

(class Code of Obj
	(properties)
	
	(method (doit)
	)
)

(class Collect of Obj
	(properties
		elements 0
		size 0
	)
	
	(method (showStr param1)
		(Format param1 999 0 name size)
	)
	
	(method (showSelf &tmp [temp0 40])
		(Print (self showStr: @temp0))
		(self eachElementDo: #showSelf)
	)
	
	(method (add param1 &tmp temp0 temp1 temp2)
		(if (not elements) (= elements (NewList)))
		(= temp1 0)
		(while (< temp1 argc)
			(AddToEnd
				elements
				(NewNode [param1 temp1] [param1 temp1])
			)
			(++ size)
			(++ temp1)
		)
		(return self)
	)
	
	(method (delete param1 &tmp temp0)
		(= temp0 0)
		(while (< temp0 argc)
			(if (DeleteKey elements [param1 temp0]) (-- size))
			(++ temp0)
		)
		(return self)
	)
	
	(method (dispose)
		(if elements
			(self eachElementDo: #dispose)
			(DisposeList elements)
		)
		(= elements 0)
		(= size 0)
		(super dispose:)
	)
	
	(method (first)
		(FirstNode elements)
	)
	
	(method (next param1)
		(NextNode param1)
	)
	
	(method (isEmpty)
		(return (if (or (== elements 0) (EmptyList elements)) 1 else 0))
	)
	
	(method (contains param1)
		(FindKey elements param1)
	)
	
	(method (eachElementDo param1 &tmp temp0 temp1 temp2)
		(= temp0 (FirstNode elements))
		(while temp0
			(= temp1 (NextNode temp0))
			(if (not (IsObject (= temp2 (NodeValue temp0))))
				(return)
			)
			(temp2 param1: &rest)
			(= temp0 temp1)
		)
	)
	
	(method (firstTrue param1 &tmp temp0 temp1 temp2)
		(= temp0 (FirstNode elements))
		(while temp0
			(= temp1 (NextNode temp0))
			(= temp2 (NodeValue temp0))
			(if (temp2 param1: &rest) (return temp2))
			(= temp0 temp1)
		)
		(return 0)
	)
	
	(method (allTrue param1 &tmp temp0 temp1 temp2)
		(= temp0 (FirstNode elements))
		(while temp0
			(= temp1 (NextNode temp0))
			(= temp2 (NodeValue temp0))
			(if (not (temp2 param1: &rest)) (return 0))
			(= temp0 temp1)
		)
		(return 1)
	)
	
	(method (release &tmp temp0 temp1)
		(= temp0 (FirstNode elements))
		(while temp0
			(= temp1 (NextNode temp0))
			(self delete: (NodeValue temp0))
			(= temp0 temp1)
		)
	)
)

(class List of Collect
	(properties
		elements 0
		size 0
	)
	
	(method (showStr param1)
		(Format param1 999 1 name size)
	)
	
	(method (at param1 &tmp temp0)
		(= temp0 (FirstNode elements))
		(while (and param1 temp0)
			(-- param1)
			(= temp0 (NextNode temp0))
		)
		(NodeValue temp0)
	)
	
	(method (last)
		(LastNode elements)
	)
	
	(method (prev param1)
		(PrevNode param1)
	)
	
	(method (addToFront param1 &tmp temp0)
		(if (not elements) (= elements (NewList)))
		(= temp0 (- argc 1))
		(while (<= 0 temp0)
			(AddToFront
				elements
				(NewNode [param1 temp0] [param1 temp0])
			)
			(++ size)
			(-- temp0)
		)
		(return self)
	)
	
	(method (addToEnd param1 &tmp temp0)
		(if (not elements) (= elements (NewList)))
		(= temp0 0)
		(while (< temp0 argc)
			(AddToEnd
				elements
				(NewNode [param1 temp0] [param1 temp0])
			)
			(++ size)
			(++ temp0)
		)
		(return self)
	)
	
	(method (addAfter param1 param2 &tmp temp0 temp1 temp2)
		(if (= temp2 (FindKey elements param1))
			(-- argc)
			(= temp0 0)
			(while (< temp0 argc)
				(= temp2
					(AddAfter
						elements
						temp2
						(NewNode [param2 temp0] [param2 temp0])
					)
				)
				(++ size)
				(++ temp0)
			)
		)
		(return self)
	)
	
	(method (indexOf param1 &tmp temp0 temp1)
		(= temp0 0)
		(= temp1 (FirstNode elements))
		(while temp1
			(if (== param1 (NodeValue temp1)) (return temp0))
			(++ temp0)
			(= temp1 (NextNode temp1))
		)
		(return -1)
	)
)

(class Set of List
	(properties
		elements 0
		size 0
	)
	
	(method (showStr param1)
		(Format param1 999 2 name size)
	)
	
	(method (add param1 &tmp temp0 temp1 temp2)
		(if (not elements) (= elements (NewList)))
		(= temp1 0)
		(while (< temp1 argc)
			(= temp2 [param1 temp1])
			(if (not (self contains: temp2))
				(AddToEnd elements (NewNode temp2 temp2))
				(++ size)
			)
			(++ temp1)
		)
	)
)

(class EventHandler of Set
	(properties
		elements 0
		size 0
	)
	
	(method (handleEvent event &tmp temp0 temp1 temp2)
		(= temp0 (FirstNode elements))
		(while (and temp0 (not (event claimed?)))
			(= temp1 (NextNode temp0))
			(if (not (IsObject (= temp2 (NodeValue temp0))))
				(break)
			)
			(temp2 handleEvent: event)
			(= temp0 temp1)
		)
		(event claimed?)
	)
)

(class Script of Obj
	(properties
		client 0
		state $ffff
		start 0
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		register 0
		script 0
		caller 0
		next 0
	)
	
	(method (doit &tmp theLastSeconds)
		(if script (script doit:))
		(cond 
			(cycles (if (not (-- cycles)) (self cue:)))
			(seconds
				(= theLastSeconds (GetTime 1))
				(if (!= lastSeconds theLastSeconds)
					(= lastSeconds theLastSeconds)
					(if (not (-- seconds)) (self cue:))
				)
			)
		)
	)
	
	(method (init theClient theCaller theRegister)
		(if (>= argc 1)
			((= client theClient) script: self)
			(if (>= argc 2)
				(= caller theCaller)
				(if (>= argc 3) (= register theRegister))
			)
		)
		(self changeState: start)
	)
	
	(method (dispose &tmp temp0)
		(if (IsObject script) (script dispose:))
		(if (IsObject timer) (timer dispose:))
		(if (IsObject client)
			(client
				script:
					(= temp0
						(cond 
							((IsObject next) next)
							(next (ScriptID next))
						)
					)
			)
			(cond 
				((not temp0) 0)
				((== newRoomNum curRoomNum) (temp0 init: client))
				(else (temp0 dispose:))
			)
		)
		(if
		(and (IsObject caller) (== newRoomNum curRoomNum))
			(caller cue: register)
		)
		(= script (= timer (= client (= next (= caller 0)))))
		(super dispose:)
	)
	
	(method (changeState newState)
		(= state newState)
	)
	
	(method (cue)
		(self changeState: (+ state 1) &rest)
	)
	
	(method (setScript theScript)
		(if (IsObject script) (script dispose:))
		(if theScript (theScript init: self &rest))
	)
	
	(method (handleEvent event)
		(if script (script handleEvent: event))
		(event claimed?)
	)
)

(class Event of Obj
	(properties
		type $0000
		message 0
		modifiers $0000
		y 0
		x 0
		claimed 0
	)
	
	(method (new param1 &tmp newSuper)
		(= newSuper (super new:))
		(GetEvent (if argc param1 else 32767) newSuper)
		(return newSuper)
	)
)
