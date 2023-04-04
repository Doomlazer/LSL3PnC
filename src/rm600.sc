;;; Sierra Script 1.0 - (do not remove this comment)
(script# 600)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use System)

(public
	rm600 0
)

(instance rm600 of Rm
	(properties
		picture 99
	)
	
	(method (init)
		(HandsOff)
		(Bset 3)
		(Bset 5)
		(Bset 4)
		(gTheMusic number: 600 loop: -1 play:)
		(super init:)
		(= currentStatus (theGame setSpeed: 3))
		(ego view: 601 setLoop: 1 setStep: 22 12 init:)
		(self setScript: RoomScript)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (changeState newState)
		(ego
			posn: (Random 10 310) -40
			setMotion: MoveTo (Random 10 310) 224 RoomScript
		)
		(switch (= state newState)
			(2 (Printf 600 0 filthStr))
			(4 (Print 600 1))
			(5 (ego setLoop: 0))
			(8 (Print 600 2))
			(9 (Print 600 3))
			(10
				(theGame setSpeed: currentStatus)
				(gTheMusic fade:)
				(curRoom newRoom: 610)
			)
		)
	)
)
