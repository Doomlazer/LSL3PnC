;;; Sierra Script 1.0 - (do not remove this comment)
(script# 41)
(include sci.sh)
(use Main)
(use Intrface)
(use Jump)
(use Motion)
(use Game)
(use System)

(public
	rm41 0
)

(local
	egoPri
	jumpY
	egoX
	egoY
	[msgBuf 44]
	[titleBuf 22]
)
(instance rm41 of Rgn
	(properties)
	
	(method (init)
		(Load rsSOUND 4)
		(if musicLoop (Load rsVIEW 813) else (Load rsVIEW 713))
		(super init:)
		(self setScript: FallScript)
	)
	
	(method (notify param1 param2)
		(= egoPri param1)
		(= jumpY param2)
		(FallScript changeState: 1)
	)
)

(instance FallScript of Script
	(properties)
	
	(method (doit)
		(if (and global64 (== gCurRoomNum 0))
			(= egoX (ego x?))
			(= egoY (ego y?))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(orchidSeconds number: 4 loop: 1 play:)
				(Print (Format @msgBuf 41 0 filthStr) #at -1 10 #dispose)
				(= gCurRoomNum 2)
				(ego
					view: (if musicLoop 813 else 713)
					setLoop: 0
					cel: 0
					illegalBits: 0
					ignoreActors:
					setPri: egoPri
					setCycle: End self
				)
			)
			(2 (ego setMotion: theJump))
			(3
				(cls)
				(if global64
					(NormalEgo)
					(= gCurRoomNum 0)
					(ego posn: egoX egoY)
				else
					(theGame setScript: (ScriptID 40))
					((ScriptID 40)
						caller: (+ 1 (ego view?))
						register: (Format @msgBuf 41 1 global82)
						next: (Format @titleBuf 41 2)
					)
				)
			)
		)
	)
)

(instance theJump of Jump
	(properties)
	
	(method (init)
		(super init: ego FallScript)
		(self yStep: 5 y: jumpY)
	)
)
