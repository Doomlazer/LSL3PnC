;;; Sierra Script 1.0 - (do not remove this comment)
(script# 120)
(include sci.sh)
(use Main)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	rm120 0
)

(local
	local0
	[str 20]
)
(procedure (localproc_000c param1 param2 param3 param4 param5)
	(Display
		param5
		dsCOORD
		param1
		param2
		dsFONT
		param4
		dsCOLOR
		(- param3 8)
	)
	(Display
		param5
		dsCOORD
		(+ param1 1)
		(+ param2 1)
		dsFONT
		param4
		dsCOLOR
		param3
	)
)

(instance rm120 of Rm
	(properties
		picture 120
		style $0003
	)
	
	(method (init)
		(HandsOff)
		(theGame setSpeed: 6)
		(SL disable:)
		(TheMenuBar hide:)
		(Bset 3)
		(Bset 5)
		(Bset 4)
		(Load rsVIEW 110)
		(Load rsVIEW 120)
		(Load rsSOUND 110)
		(Load rsSOUND 120)
		(Load rsFONT 9)
		(super init:)
		(addToPics
			add: atpLeg1
			add: atpLeg2
			add: atpLeg3
			add: atpLeg4
			doit:
		)
		(self setScript: RoomScript)
		(ego init: hide:)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== -1 (gTheMusic prevSignal?)) (== state 7))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(gTheMusic stop: number: 110 loop: 1 play:)
				(= cycles 16)
			)
			(2
				(aLogoDissolve
					setCel: 255
					setCycle: Beg self
					setPri: 10
					init:
				)
				(aLogo setPri: 8 init:)
			)
			(3
				(aLogoDissolve dispose:)
				(aLogo stopUpd:)
				(= cycles 16)
			)
			(4
				(aSierra init: setCycle: End self)
			)
			(5
				(aSierra stopUpd:)
				(= cycles 16)
			)
			(6
				(aPresents init: setCycle: End self)
			)
			(7 (aPresents stopUpd:))
			(8 (= cycles 12))
			(9
				(gTheMusic prevSignal: 0 stop: number: 120 loop: -1 play:)
				(aLogo dispose:)
				(aSierra dispose:)
				(aPresents dispose:)
				(curRoom drawPic: 120)
				(aThigh init:)
				(aCalf init:)
				(= cycles 2)
			)
			(10
				(localproc_000c 128 30 9 9 (Format @str 120 0))
				(localproc_000c
					147
					58
					12
					9
					(Format @str {Point and Click})
				)
				(= cycles 10)
			)
			(11
				(localproc_000c 146 90 9 9 (Format @str 120 1))
				(= cycles 10)
			)
			(12
				(localproc_000c 143 110 9 9 (Format @str 120 2))
				(= cycles 10)
			)
			(13
				(localproc_000c 132 130 9 9 (Format @str 120 3))
				(= cycles 10)
			)
			(14
				(aThigh setCycle: Fwd)
				(aCalf setCycle: Fwd)
				(= cycles 16)
			)
			(15 (curRoom newRoom: 130))
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?))
			(
				(and
					(== (event type?) evKEYBOARD)
					(== (event message?) KEY_F2)
				)
				(ToggleSound)
			)
			(else
				(if (!= 120 (gTheMusic number?))
					(gTheMusic number: 120 loop: -1 play:)
				)
				(curRoom newRoom: 140)
			)
		)
	)
)

(instance aSierra of Prop
	(properties
		y 45
		x 234
		view 110
		priority 1
		cycleSpeed 1
	)
)

(instance aPresents of Prop
	(properties
		y 146
		x 237
		view 110
		loop 1
		priority 10
		cycleSpeed 1
	)
)

(instance aLogo of Prop
	(properties
		y 124
		x 236
		view 110
		loop 2
	)
)

(instance aLogoDissolve of Prop
	(properties
		y 124
		x 236
		view 110
		loop 3
		cel 255
		cycleSpeed 3
	)
)

(instance aThigh of Prop
	(properties
		y 66
		x 78
		view 120
		priority 3
		cycleSpeed 1
	)
)

(instance aCalf of Prop
	(properties
		y 149
		x 66
		view 120
		loop 1
		priority 4
		cycleSpeed 1
	)
)

(instance atpLeg1 of PV
	(properties
		y 36
		x 37
		view 120
		loop 2
		priority 3
	)
)

(instance atpLeg2 of PV
	(properties
		y 84
		x 26
		view 120
		loop 2
		cel 1
		priority 3
	)
)

(instance atpLeg3 of PV
	(properties
		y 129
		x 18
		view 120
		loop 2
		cel 2
		priority 3
	)
)

(instance atpLeg4 of PV
	(properties
		y 190
		x 25
		view 120
		loop 2
		cel 3
		priority 3
	)
)
