;;; Sierra Script 1.0 - (do not remove this comment)
(script# 993)
(include sci.sh)
(use System)


(class gamefile_sh of Obj
	(properties
		handle 0
		name "gamefile.sh"
	)
	
	(method (open param1)
		(= handle
			(switch argc
				(0 (FOpen name 0))
				(1 (FOpen name param1))
				(else  0)
			)
		)
		(if (== handle -1) (= handle 0))
		(return (if handle self else 0))
	)
	
	(method (write param1 &tmp temp0)
		(if (not handle) (self open:))
		(if handle
			(= temp0 0)
			(while (< temp0 argc)
				(FPuts handle [param1 temp0])
				(++ temp0)
			)
		)
	)
	
	(method (read param1 param2)
		(if (!= argc 2) (return 0))
		(if (not handle) (self open: 1))
		(return (if handle (FGets param1 param2 handle) else 0))
	)
	
	(method (close)
		(if handle (FClose handle) (= handle 0))
	)
	
	(method (dispose)
		(self close:)
		(super dispose:)
	)
	
	(method (showStr param1)
		(Format param1 993 0 name)
	)
)
