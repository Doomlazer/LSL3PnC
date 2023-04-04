;;; Sierra Script 1.0 - (do not remove this comment)
(script# 21)
(include sci.sh)
(use Main)

(public
	ChangeScriptState 1
)

(procedure (ChangeScriptState param1 param2 param3 param4 &tmp [temp0 33])
	(if (and debugging (not (Btst 14)))
		(if (< argc 2) (= param3 1))
		(if (< argc 3) (= param4 7))
		(Display
			(Format
				@temp0
				21
				0
				(param1 name?)
				(param1 state?)
				param2
			)
			dsCOORD
			1
			(- (* 8 param3) 7)
			dsFONT
			999
			dsCOLOR
			param4
			dsBACKGROUND
			0
		)
	)
)
