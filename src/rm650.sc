;;; Sierra Script 1.0 - (do not remove this comment)
(script# 650)
(include sci.sh)
(use Main)
(use Intrface)
(use Reverse)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm650 0
)

(local
	local0
	congratsMsg
	[plotString 222]
)
(procedure (localproc_000c param1 &tmp temp0)
	(if (< argc 1) (= param1 200))
	(Print
		@plotString
		#at
		-1
		10
		#width
		param1
		#time
		(= temp0 (PrintDelay @plotString))
		#dispose
	)
	(return (+ 2 temp0))
)

(instance rm650 of Rm
	(properties
		picture 650
	)
	
	(method (init)
		(HandsOff)
		(Load rsSCRIPT 969)
		(aLeg init:)
		(aMonitor init:)
		(aLeftHand init:)
		(aRightHand init:)
		(aDoor init:)
		(aTheEnd init:)
		(super init:)
		(addToPics
			add: atpKeyboard
			add: atpGlass
			add: atpBigGlass
			doit:
		)
		(self setScript: RoomScript)
		(= currentStatus (theGame setSpeed: 6))
		(NormalEgo 6 650)
		(HandsOff)
		(ego
			posn: 999 999
			setPri: 15
			setStep: 1 1
			setLoop: 6
			ignoreActors:
			moveSpeed: 2
			init:
		)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(Format @plotString 650 0)
				(= seconds (localproc_000c))
			)
			(2
				(Format @plotString 650 1)
				(= seconds (localproc_000c))
			)
			(3
				(Format @plotString 650 2)
				(= seconds (localproc_000c 122))
			)
			(4
				(Format @plotString 650 3)
				(= seconds (localproc_000c 111))
			)
			(5
				(Format @plotString 650 4)
				(= seconds (localproc_000c))
			)
			(6
				(Format @plotString 650 5)
				(aMonitor hide:)
				(= seconds (localproc_000c))
			)
			(7
				(aMonitor show: setLoop: 5 setCycle: Fwd)
				(aDoor posn: 64 99)
				(ego posn: 64 103)
				(= seconds 6)
			)
			(8
				(ego setMotion: MoveTo 64 100 self)
			)
			(9 (aDoor setCycle: End self))
			(10
				(ego posn: 999 999)
				(= seconds 3)
			)
			(11 (aDoor setCycle: Beg self))
			(12 (= seconds 6))
			(13
				(aMonitor hide:)
				(aDoor posn: 234 345)
				(ego posn: 999 999)
				(= seconds 3)
			)
			(14
				(aMonitor show: loop: 3 setCycle: Fwd)
				(= seconds 3)
			)
			(15
				(if congratsMsg
					(= seconds 7)
					(= state 6)
				else
					(aTheEnd init: setCycle: End self)
				)
			)
			(16
				(aTheEnd setLoop: 1 cycleSpeed: 3 setCycle: Fwd)
				(= seconds 3)
			)
			(17
				(= congratsMsg 1)
				(Format @plotString 650 6)
				(= seconds (localproc_000c))
			)
			(18
				(Format
					@plotString
					{Version Point and Click By: DoomLazer and Pakolmo\nacknowledgements: Kokeween, walas74, Benito Camelo and Stormspirit86}
				)
				(= seconds (localproc_000c))
			)
			(19
				(Format @plotString 650 7)
				(= seconds (localproc_000c))
			)
			(20
				(if
					(and
						(Btst 11)
						(Btst 56)
						(Btst 52)
						(Btst 43)
						(not (Btst 77))
						(not (Btst 70))
						(Btst 45)
					)
					(Format @plotString 650 8)
				else
					(Format @plotString 650 9)
				)
				(= seconds (localproc_000c))
			)
			(21
				(if (not (Btst 77))
					(self cue:)
				else
					(Format @plotString 650 10)
					(= seconds (localproc_000c))
				)
			)
			(22
				(if (Btst 43)
					(self cue:)
				else
					(Format @plotString 650 11)
					(= seconds (localproc_000c))
				)
			)
			(23
				(if (Btst 70)
					(Format @plotString 650 12)
					(= seconds (localproc_000c))
				else
					(self cue:)
				)
			)
			(24
				(if (Btst 11)
					(self cue:)
				else
					(Format @plotString 650 13)
					(= seconds (localproc_000c))
				)
			)
			(25
				(if (not (Btst 56))
					(Format @plotString 650 14)
					(= seconds (localproc_000c))
				else
					(self cue:)
				)
			)
			(26
				(if (Btst 52)
					(self cue:)
				else
					(Format @plotString 650 15)
					(= seconds (localproc_000c))
				)
			)
			(27
				(if (Btst 45)
					(self cue:)
				else
					(Format @plotString 650 16)
					(= seconds (localproc_000c))
				)
			)
			(28 (= state 6))
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (super handleEvent: event))
				(not (event claimed?))
				modelessDialog
				seconds
				(== (event message?) KEY_RETURN)
				(== (event type?) evKEYBOARD)
			)
			(event claimed: 1)
			(= seconds 0)
			(cls)
			(self cue:)
		)
	)
)

(instance atpKeyboard of PV
	(properties
		y 189
		x 60
		view 650
		priority 14
	)
)

(instance atpGlass of PV
	(properties
		y 158
		x 221
		view 650
		cel 1
		priority 15
		signal $4000
	)
)

(instance atpBigGlass of PV
	(properties
		y 173
		x 192
		view 650
		cel 2
		priority 15
		signal $4000
	)
)

(instance aRightHand of Prop
	(properties
		y 173
		x 103
		view 650
		loop 1
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 setCycle: Fwd ignoreActors:)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(cond 
			((== (= temp0 (Random 0 33)) 1) (self posn: 100 178))
			((== temp0 2) (self posn: 101 184))
			((== temp0 3) (self posn: 34 180))
			((== temp0 4) (self posn: 36 184))
			((< temp0 17) (self cel: 0))
			(else (self cel: 1))
		)
	)
)

(instance aLeftHand of Prop
	(properties
		y 173
		x 17
		view 650
		loop 2
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 setCycle: Fwd ignoreActors:)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(cond 
			((== (= temp0 (Random 0 33)) 1) (self posn: 11 173))
			((== temp0 2) (self posn: 18 180))
			((== temp0 3) (self posn: 8 185))
			((< temp0 17) (self cel: 0))
			(else (self cel: 1))
		)
	)
)

(instance aMonitor of Prop
	(properties
		y 104
		x 61
		view 650
		loop 3
	)
	
	(method (init)
		(super init:)
		(self setPri: 4 setCycle: Fwd ignoreActors:)
	)
	
	(method (doit)
		(super doit:)
		(if (== loop 3)
			(switch (Random 0 7)
				(1 (self setCycle: Fwd))
				(2 (self setCycle: Rev))
				(3
					(self cycleSpeed: (Random 0 1))
				)
			)
		)
	)
)

(instance aLeg of Prop
	(properties
		y 189
		x 306
		view 650
		loop 4
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Fwd)
	)
	
	(method (doit)
		(super doit:)
		(switch (Random 0 7)
			(0 (self setCycle: Fwd))
			(7 (self setCel:))
		)
	)
)

(instance aDoor of Prop
	(properties
		y 990
		x 640
		view 650
		loop 7
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 7 ignoreActors:)
	)
)

(instance aTheEnd of Prop
	(properties
		y 47
		x 96
		view 651
	)
)
