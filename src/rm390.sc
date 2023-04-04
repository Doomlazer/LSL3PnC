;;; Sierra Script 1.0 - (do not remove this comment)
(script# 390)
(include sci.sh)
(use Main)
(use n021)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm390 0
)
(synonyms
	(babe bambi dale)
)

(local
	hitRearWall
	[str 200]
)
(procedure (localproc_001a)
	(cls)
	(Print
		@str
		#at
		10
		10
		#title
		{Bambi says...}
		#width
		140
		#mode
		1
		#dispose
	)
)

(procedure (localproc_0040)
	(cls)
	(Print
		@str
		#at
		160
		10
		#title
		{You say...}
		#width
		140
		#mode
		1
		#dispose
	)
)

(instance rm390 of Rm
	(properties
		picture 390
	)
	
	(method (init)
		(aMike init:)
		(super init:)
		(if (and (not (Btst 52)) (not (Btst 53)))
			(aMonitorRight init:)
			(aMonitorLeft init:)
			(aBambi init:)
		)
		(self setScript: RoomScript)
		(if (== prevRoomNum 395)
			(ego posn: 135 155 loop: 2)
			(if (!= gCurRoomNum 1)
				(= gCurRoomNum 0)
			else
				(gTheMusic stop:)
				(Load rsVIEW 392)
				(Load rsVIEW 393)
				(Load rsVIEW 397)
				(Load rsSOUND 390)
				(aBambi setScript: 0)
				(RoomScript changeState: 1)
			)
		else
			(ego posn: 243 134 setLoop: 1)
		)
		(if (!= gCurRoomNum 1)
			(NormalEgo)
			(if (cast contains: aBambi)
				(BambiScript changeState: 1)
			)
		)
		(ego init:)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (< (ego priority?) 5)
			(if (not hitRearWall)
				(= hitRearWall 1)
				(ShakeScreen 1 1)
				(Print 390 0)
				(ego setMotion: 0)
			)
		else
			(= hitRearWall 0)
		)
		(if (& (ego onControl:) $0002) (curRoom newRoom: 360))
	)
	
	(method (changeState newState)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(gTheMusic stop:)
				(= cycles 2)
				(= seconds 3)
			)
			(2 (gTheMusic stop:))
			(3
				(gTheMusic stop:)
				(= currentStatus (theGame setSpeed: 6))
				(Print 390 10)
				(gTheMusic number: 390 loop: -1 play: self)
				(aSpeakerLeft init:)
				(aSpeakerRight init:)
				(aBambi cycleSpeed: 1 setCycle: Fwd)
				(= cycles 30)
			)
			(4
				(aBambi setLoop: 0)
				(= cycles 30)
			)
			(5
				(aBambi setLoop: 1)
				(= cycles 30)
			)
			(6
				(aBambi setLoop: 2 setCel: 0 setCycle: End self)
			)
			(7
				(aBambi setLoop: 3 setCycle: Fwd)
				(= cycles 30)
			)
			(8
				(aBambi setLoop: 2 setCel: 255 setCycle: Beg self)
			)
			(9
				(aBambi setLoop: 4 setCycle: Fwd)
				(= cycles 30)
			)
			(10
				(Format @str 390 11)
				(localproc_0040)
				(aBambi view: 392 setLoop: 0 cycleSpeed: 0)
				(= cycles 30)
			)
			(11
				(Format @str 390 12)
				(localproc_001a)
				(aBambi setLoop: 1)
				(= cycles 30)
			)
			(12
				(Format @str 390 13)
				(localproc_0040)
				(= cycles 30)
			)
			(13
				(aMike setCycle: End)
				(= cycles 30)
			)
			(14
				(aMike stopUpd:)
				(aBambi setLoop: 2 setCel: 0 setCycle: End self)
			)
			(15
				(aBambi setLoop: 3 setCycle: Fwd)
				(= cycles 30)
			)
			(16
				(Format @str 390 14)
				(localproc_0040)
				(aBambi setLoop: 4)
				(= cycles 30)
			)
			(17
				(aBambi setLoop: 5 setCel: 0 setCycle: End self)
			)
			(18
				(Format @str 390 15)
				(localproc_001a)
				(aBambi setLoop: 6 setCycle: Fwd)
				(= cycles 30)
			)
			(19
				(aMonitorLeft dispose:)
				(aMonitorRight dispose:)
				(aLens init: setCycle: End self)
			)
			(20
				(aLens stopUpd:)
				(= seconds 5)
			)
			(21
				(= seconds 0)
				(Format @str 390 16)
				(localproc_001a)
				(gTheMusic fade:)
				(aBambi
					view: 393
					setPri: -1
					setLoop: 0
					posn: 157 155
					setCycle: Walk
					setMotion: MoveTo 180 154 self
				)
			)
			(22
				(aBambi setMotion: MoveTo 200 134 self)
			)
			(23
				(ego
					setCycle: Walk
					setLoop: -1
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 180 154 self
				)
				(aBambi setScript: BambiScript)
				(BambiScript changeState: 6)
				(theGame setSpeed: currentStatus)
			)
			(24
				(ego setMotion: MoveTo 200 134 self)
			)
			(25
				(ego setMotion: MoveTo 333 134)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			((Said 'nightstand,get,jump,climb<on/backstage') (Print 390 1))
			((Said 'get') (Print 390 2))
			((Said 'drag<on/camera') (Print 390 3))
			((Said 'make/tape') (Print 390 4))
			(
				(or
					(Said '/class,(work<out),aerobic')
					(Said '//class,(work<out),aerobic')
					(Said 'class,dance,naked,naked,(work<out),class')
				)
				(if currentEgo (Print 390 5) else (Print 390 6))
			)
			((Said 'look>')
				(cond 
					(
					(Said '/camera,equipment,camera,tape,microphone') (Print 390 7))
					((Said '/burn') (Print 390 8))
					((Said '[/area]')
						(Printf
							390
							9
							(cond 
								((cast contains: aBambi)
									{A beautiful blond woman stands on the stage with a puzzled expression on her face.}
								)
								((Btst 53) {It looks like someone was recently here making a video.})
								(else
									{The equipment seems to have suffered some sort of meltdown!}
								)
							)
						)
					)
				)
			)
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emSHIFT))
				)
				(if
					(and
						(proc0_26 aBambi (event x?) (event y?))
						(cast contains: aBambi)
					)
					(event claimed: 1)
					(switch theCursor
						(998
							(cond 
								((not (& (ego onControl:) $0008)) (Print 390 17))
								((Btst 8) (Print 390 18))
								((Btst 10) (Print 390 19))
								((Btst 62) (Print 390 20))
								(else (BambiScript changeState: 4))
							)
						)
						(994
							(if (not (& (ego onControl:) $0008))
								(NotClose)
							else
								(Print 390 23)
							)
						)
						(995
							(Print 390 21)
							(Print 390 22 #at -1 144)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 232)
						(< (event x?) 251)
						(> (event y?) 70)
						(< (event y?) 137)
					)
					(event claimed: 1)
					(switch theCursor
						(999
							(ego setMotion: MoveTo 276 131)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 1)
						(< (event x?) 232)
						(> (event y?) 21)
						(< (event y?) 136)
					)
					(event claimed: 1)
					(switch theCursor
						(998
							(Printf
								390
								9
								(cond 
									((cast contains: aBambi)
										{A beautiful blond woman stands on the stage with a puzzled expression on her face.}
									)
									((Btst 53) {It looks like someone was recently here making a video.})
									(else
										{The equipment seems to have suffered some sort of meltdown!}
									)
								)
							)
						)
						(else  (event claimed: 0))
					)
				)
			)
		)
	)
)

(instance aBambi of Act
	(properties
		y 155
		x 160
		view 391
	)
	
	(method (init)
		(super init:)
		(self
			illegalBits: 0
			ignoreHorizon:
			ignoreActors:
			setPri: 15
			setScript: BambiScript
		)
	)
)

(instance BambiScript of Script
	(properties)
	
	(method (changeState newState)
		(ChangeScriptState self newState 2 2)
		(switch (= state newState)
			(0)
			(1
				(aBambi
					setCycle: Fwd
					setLoop:
					(switch (Random 0 2)
						(0 0)
						(1 1)
						(else  4)
					)
				)
				(= seconds (Random 5 11))
			)
			(2
				(aBambi setLoop: 1 setCel: 0)
				(= seconds (Random 2 4))
				(= state 0)
			)
			(3)
			(4
				(aBambi setLoop: 1 setCel: 0)
				(if (Btst 71)
					(Print 390 24)
				else
					(Printf 390 25 global171)
				)
				(= seconds 3)
			)
			(5
				(if (Btst 71)
					(Print 390 26)
				else
					(Bset 71)
					(if currentEgo (Print 390 27) else (Print 390 28))
				)
				(= gCurRoomNum 14)
				(curRoom newRoom: 395)
			)
			(6
				(aBambi setMotion: MoveTo 333 134)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			((Said 'look/babe')
				(cond 
					((not (& (ego onControl:) $0008)) (Print 390 17))
					((Btst 8) (Print 390 18))
					((Btst 10) (Print 390 19))
					((Btst 62) (Print 390 20))
					(else (self changeState: 4))
				)
			)
			((Said '/boob,body') (Print 390 21) (Print 390 22 #at -1 144))
			((Said '/babe')
				(if (not (& (ego onControl:) $0008))
					(NotClose)
				else
					(Print 390 23)
				)
			)
		)
	)
)

(instance aSpeakerLeft of Prop
	(properties
		y 154
		x 36
		view 390
		loop 2
		cel 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 setCycle: Fwd)
	)
)

(instance aSpeakerRight of Prop
	(properties
		y 153
		x 294
		view 390
		loop 3
		cel 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 setCycle: Fwd)
	)
)

(instance aLens of Prop
	(properties
		y 73
		x 60
		view 390
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 9 ignoreActors:)
	)
)

(instance aMike of Prop
	(properties
		y 61
		x 168
		view 390
		loop 1
		cycleSpeed 2
	)
	
	(method (init)
		(super init:)
		(self setPri: 10 ignoreActors:)
	)
)

(instance aMonitorLeft of Prop
	(properties
		y 121
		x 93
		view 396
	)
	
	(method (init)
		(super init:)
		(self setPri: 11 ignoreActors:)
	)
	
	(method (doit)
		(self
			view: (+ 5 (aBambi view?))
			loop: (aBambi loop?)
			cel: (aBambi cel?)
		)
		(super doit:)
	)
)

(instance aMonitorRight of Prop
	(properties
		y 121
		x 229
		view 396
	)
	
	(method (init)
		(super init:)
		(self setPri: 11 setCycle: Fwd ignoreActors:)
	)
	
	(method (doit)
		(self
			view: (+ 5 (aBambi view?))
			loop: (aBambi loop?)
			cel: (aBambi cel?)
		)
		(super doit:)
	)
)
