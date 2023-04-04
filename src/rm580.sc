;;; Sierra Script 1.0 - (do not remove this comment)
(script# 580)
(include sci.sh)
(use Main)
(use n021)
(use Intrface)
(use Jump)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm580 0
)

(instance rm580 of Rm
	(properties
		picture 580
	)
	
	(method (init)
		(HandsOff)
		(Load rsVIEW 580)
		(Load rsVIEW 582)
		(Load rsVIEW 583)
		(Load rsSOUND 580)
		(Load rsSOUND 581)
		(Load rsSOUND 585)
		(Load rsSCRIPT 991)
		(super init:)
		(ego
			view: 581
			setLoop: 0
			setStep: 2 2
			posn: 370 10
			init:
			setCel: 1
		)
		(self setScript: RoomScript)
		(User canInput: 0 mapKeyToDir: 0)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 100])
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 264 53 self)
			)
			(1
				(Print 580 0 #at 10 -1 #width 290)
				(ego viewer: HeadTurner setMotion: MoveTo 130 97)
				(gTheMusic fade:)
				(= cycles 60)
			)
			(2
				(gTheMusic number: 580 loop: 1 play:)
				(aWoman1
					ignoreHorizon:
					ignoreActors:
					view: 580
					setLoop: 0
					setPri: 8
					setStep: 1 1
					setMotion: JumpTo 128 61 self
					init:
				)
			)
			(3
				(Print 580 1 #at -1 10 #dispose)
				(aWoman1 view: 582 setLoop: 1 posn: 94 -2)
				(ego viewer: 0 setLoop: 1 cel: 0 setCycle: End self)
			)
			(4
				(ego setLoop: 2 cel: 0 setCycle: End self)
			)
			(5
				(ego setLoop: 3 setCel: 255 setCycle: Beg self)
			)
			(6
				(gTheMusic number: 581 loop: 1 play:)
				(cls)
				(ego setCycle: End)
				(aWoman2 init:)
				(aWoman1
					setCycle: Fwd
					setStep: 1 1
					setMotion: JumpTo 114 97 self
				)
			)
			(7
				(ego hide: view: 580 setLoop: 1 cel: 0 posn: 89 118)
				(aWoman1 setLoop: 2 cel: 0 setCycle: End self)
			)
			(8
				(ego setCycle: End setMotion: MoveTo -47 164 show:)
				(= cycles 11)
			)
			(9 (Print 580 2) (= cycles 22))
			(10
				(Woman2Script cue:)
				(aWoman1 setLoop: 3 cel: 0 setCycle: End self)
			)
			(11
				(gTheMusic number: 585 loop: -1 play:)
				(aWoman1
					setLoop: 4
					setCycle: Walk
					setStep: 3 2
					setMotion: MoveTo 44 77 self
				)
				(= cycles 15)
			)
			(12
				(Print
					(Format
						@temp0
						580
						3
						filthStr
						(if (>= global88 3) { lesbian} else {})
					)
					#at
					10
					-1
					#width
					290
				)
			)
			(13
				(aWoman1
					setLoop: 5
					setCycle: Walk
					setMotion: MoveTo -20 60
				)
				(= cycles 11)
			)
			(14 (Print 580 4 #at -1 144))
		)
	)
)

(instance aWoman1 of Act
	(properties
		y -2
		x 128
		view 580
		illegalBits $0000
	)
)

(instance aWoman2 of Act
	(properties
		y -11
		x 161
		view 583
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			ignoreActors:
			setScript: Woman2Script
		)
	)
)

(instance Woman2Script of Script
	(properties)
	
	(method (changeState newState)
		(ChangeScriptState self newState 2 2)
		(switch (= state newState)
			(0
				(aWoman2
					setLoop: 1
					setCycle: Fwd
					setStep: 1 1
					setMotion: JumpTo 141 94 self
				)
			)
			(1
				(aWoman2 setLoop: 2 cel: 0 setCycle: End self)
			)
			(2)
			(3
				(aWoman2 setLoop: 3 cel: 0 setCycle: End self)
			)
			(4
				(aWoman2
					setLoop: 4
					setCycle: Walk
					setStep: 3 2
					setMotion: MoveTo 66 73 self
				)
			)
			(5
				(aWoman2 setLoop: 5 setMotion: MoveTo -20 60 self)
			)
			(6 (curRoom newRoom: 585))
		)
	)
)

(instance HeadTurner of Code
	(properties)
	
	(method (doit)
		(if (not (Random 0 3)) (ego setCel: (Random 0 4)))
	)
)
