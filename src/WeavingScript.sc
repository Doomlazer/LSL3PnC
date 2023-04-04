;;; Sierra Script 1.0 - (do not remove this comment)
(script# 44)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Invent)
(use System)

(public
	WeavingScript 0
)

(local
	oldIllBits
)
(instance WeavingScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= currentStatus (theGame setSpeed: 6))
				(= oldIllBits (ego illegalBits?))
				(ego
					view: 717
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
				(Print 44 0 #icon 4 0 0)
			)
			(1
				(ego loop: 1 cel: 0 setCycle: Fwd)
				(= seconds 3)
			)
			(2 (ego loop: 2) (= seconds 2))
			(3
				(ego loop: 3 cel: 0 setCycle: End self)
			)
			(4
				((Inv at: 4) view: 23)
				(Format ((Inv at: 4) name?) {Native Grass_})
				(theGame changeScore: 30)
				(NormalEgo 0)
				(ego illegalBits: oldIllBits)
				(theGame setScript: 0 setSpeed: currentStatus)
				(DisposeScript 44)
			)
		)
	)
)
