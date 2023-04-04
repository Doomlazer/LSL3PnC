;;; Sierra Script 1.0 - (do not remove this comment)
(script# 43)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Invent)
(use System)

(public
	CarvingScript 0
)

(local
	oldIllBits
)
(instance CarvingScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= gCurRoomNum 0)
					(= cycles 1)
				else
					(HandsOff)
					(= currentStatus (theGame setSpeed: 6))
					(= oldIllBits (ego illegalBits?))
					(ego view: 711 loop: 0 cel: 0 setCycle: End self)
				)
				(Print 43 0 #icon 3 0 0)
			)
			(1
				(if (!= gCurRoomNum 0)
					(= cycles 1)
				else
					(ego loop: 1 cel: 0 setCycle: Fwd)
					(= cycles (* 10 (NumCels ego)))
				)
			)
			(2
				((Inv at: 3) view: (if (>= global88 2) 22 else 34))
				(Format ((Inv at: 3) name?) 43 1)
				(theGame changeScore: 50)
				(if (== gCurRoomNum 0)
					(HandsOn)
					(NormalEgo 0)
					(ego illegalBits: oldIllBits)
					(theGame setSpeed: currentStatus)
				)
				(theGame setScript: 0)
				(DisposeScript 43)
			)
		)
	)
)
