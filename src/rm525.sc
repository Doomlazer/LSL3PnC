;;; Sierra Script 1.0 - (do not remove this comment)
(script# 525)
(include sci.sh)
(use Main)
(use Intrface)
(use Jump)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm525 0
)

(local
	[msgBuf 40]
	[titleBuf 22]
)
(instance rm525 of Rm
	(properties
		picture 525
		horizon 1
	)
	
	(method (init)
		(Load rsVIEW 525)
		(Load rsSOUND 3)
		(Load rsSOUND 526)
		(Load rsSOUND 527)
		(Load rsSOUND 4)
		(HandsOff)
		(cls)
		(= currentStatus (theGame setSpeed: 6))
		(super init:)
		(User canInput: 0 mapKeyToDir: 0)
		(ego
			view: 525
			setLoop: (if (== gCurRoomNum 2) 4 else 0)
			setCel: 0
			setStep: 0 7
			setMotion: 0
			setCycle: (if (== gCurRoomNum 2) Fwd else 0)
			posn: 142 13
			cycleSpeed: 1
			illegalBits: 0
			ignoreActors:
			init:
			put: 15 -1
		)
		(self setScript: RoomScript)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= gCurRoomNum 2)
					(= cycles 10)
				else
					(gTheMusic number: 4 loop: 1 play:)
					(ego setMotion: MoveTo 144 18 self)
					(= state 19)
				)
			)
			(1
				(Print 525 0)
				(Print 525 1 #at -1 144)
				(= cycles 20)
			)
			(2
				(gTheMusic stop: number: 3 loop: 1 play:)
				(ego setCycle: End self)
			)
			(3 (ego setCycle: Beg self))
			(4
				(gTheMusic stop: number: 526 loop: 1 play:)
				(ego setCycle: End self)
			)
			(5
				(ego setLoop: 1 setCel: 0 setCycle: End self)
			)
			(6 (ego setCycle: Beg self))
			(7
				(ego setLoop: 0 setCel: 255 setCycle: Beg self)
			)
			(8
				(gTheMusic stop: number: 527 loop: 1 play:)
				(ego setCycle: End self)
			)
			(9
				(ego setLoop: 1 setCel: 0 setCycle: End self)
			)
			(10
				(ego setLoop: 2 setCel: 0 setCycle: End self)
			)
			(11 (ego setCycle: Beg self))
			(12
				(ego setLoop: 1 setCel: 255 setCycle: Beg self)
			)
			(13
				(ego setLoop: 0 setCel: 255 setCycle: Beg self)
			)
			(14
				(gTheMusic stop: number: 527 loop: 1 play:)
				(ego setCycle: End self)
			)
			(15
				(ego setLoop: 1 setCel: 0 setCycle: End self)
			)
			(16
				(ego setLoop: 2 setCel: 0 setCycle: End self)
			)
			(17
				(ego setLoop: 3 setCel: 0 setCycle: End self)
			)
			(18
				(ego setLoop: 4 setPri: -1 posn: 143 114 setCycle: Fwd)
				(aHose ignoreActors: init:)
				(Print 525 2 #at -1 10 #draw)
				(gTheMusic stop: number: 4 loop: 1 play:)
				(curRoom newRoom: 530)
			)
			(20
				(ego setMotion: theJump self)
			)
			(21
				(Print 525 3)
				(ego hide:)
				(theGame setScript: (ScriptID 40))
				((ScriptID 40)
					caller: 814
					register: (Format @msgBuf 525 4)
					next: (Format @titleBuf 525 5)
				)
			)
		)
	)
)

(instance aHose of Prop
	(properties
		y 13
		x 142
		view 525
		loop 5
	)
)

(instance theJump of Jump
	(properties)
	
	(method (init)
		(super init: ego RoomScript)
		(self yStep: 1 y: 183)
	)
)
