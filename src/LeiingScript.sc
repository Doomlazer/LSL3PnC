;;; Sierra Script 1.0 - (do not remove this comment)
(script# 42)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Invent)
(use System)

(public
	LeiingScript 0
)

(local
	oldIllBits
)
(instance LeiingScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= currentStatus (theGame setSpeed: 6))
				(= oldIllBits (ego illegalBits?))
				(ego
					view: 710
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
				(Print 42 0 #icon 11 0 0)
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
				((Inv at: 11) view: 26)
				(Format ((Inv at: 11) name?) {A Quick Lei__})
				(if (not (Btst 55))
					(Bset 55)
					(theGame changeScore: 50)
					(Print 42 2 #at -1 144)
				else
					(Print 42 3 #at -1 144)
				)
				(NormalEgo 0)
				(ego illegalBits: oldIllBits)
				(theGame setScript: 0 setSpeed: currentStatus)
				(DisposeScript 42)
			)
		)
	)
)
