;;; Sierra Script 1.0 - (do not remove this comment)
(script# 481)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	rm481 0
)

(local
	[plotString 200]
)
(procedure (localproc_000c &tmp temp0)
	(Print
		@plotString
		#at
		0
		70
		#width
		125
		#time
		(= temp0 (PrintDelay @plotString))
		#dispose
	)
	(return (+ 3 temp0))
)

(instance rm481 of Rm
	(properties
		picture 485
		horizon 5
	)
	
	(method (init)
		(HandsOff)
		(Load rsPIC 99)
		(Load rsVIEW 54)
		(Load rsFONT 7)
		(Load rsSOUND 484)
		(Load rsSOUND 485)
		(SL disable:)
		(TheMenuBar hide:)
		(gTheMusic number: 484 loop: 2 play:)
		(super init:)
		(self setScript: RoomScript)
		(aThermometerP setCycle: End init:)
		(aThermometerL setCycle: End init:)
		(systemWindow color: 7 back: 0)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(User canInput: 0 canControl: 0 mapKeyToDir: 0)
		(theGame setSpeed: 6)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 33))
			(1
				(Format @plotString 481 1)
				(= seconds (localproc_000c))
			)
			(2
				(Format @plotString 481 2)
				(= seconds (localproc_000c))
			)
			(3
				(Format @plotString 481 3)
				(= seconds (localproc_000c))
			)
			(4
				(Format @plotString 481 4)
				(= seconds (localproc_000c))
			)
			(5
				(Format @plotString 481 5)
				(= seconds (localproc_000c))
			)
			(6
				(curRoom drawPic: 99 6)
				(gTheMusic number: 485 loop: 2 play:)
				(aThermometerP setLoop: 1 cycleSpeed: 0 setCycle: Fwd)
				(aThermometerL setLoop: 1 cycleSpeed: 0 setCycle: Fwd)
				(= cycles 55)
			)
			(7
				(Print 481 6 #font 7 #at 0 90 #width 125 #time 3 #dispose)
				(aThermometerP
					setLoop: 0
					setCel: 255
					cycleSpeed: 2
					setCycle: Beg
				)
				(aThermometerL
					setLoop: 0
					setCel: 255
					cycleSpeed: 2
					setCycle: Beg
				)
				(= cycles 55)
			)
			(8
				(aThermometerP dispose:)
				(aThermometerL dispose:)
				(= cycles 33)
			)
			(9
				(Format @plotString 481 7)
				(= seconds (localproc_000c))
			)
			(10 (curRoom newRoom: 482))
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) evKEYBOARD)
				(== (event claimed?) 0)
				(== (event message?) KEY_F8)
			)
			(Print 481 0)
			(Bset 69)
			(curRoom newRoom: 484)
		)
	)
)

(instance aThermometerP of Prop
	(properties
		y 189
		x 181
		view 54
		cycleSpeed 12
	)
)

(instance aThermometerL of Prop
	(properties
		y 54
		x 101
		view 54
		cycleSpeed 12
	)
)
