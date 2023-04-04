;;; Sierra Script 1.0 - (do not remove this comment)
(script# 355)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm355 0
)

(instance rm355 of Rm
	(properties
		picture 355
	)
	
	(method (init)
		(Load rsVIEW 352)
		(Load rsVIEW 356)
		(super init:)
		(addToPics
			add: atpComputer
			add: atpPaper
			add: atpPhone
			add: atpSign
			doit:
		)
		(if (> global87 16)
			(aFountain setCycle: Fwd isExtra: 1 init:)
		)
		(aKen init:)
		(ego illegalBits: 0 posn: 290 141 init:)
		(self setScript: RoomScript)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 214 146 self)
				(= cycles 22)
			)
			(1 (aKen setCycle: Fwd))
			(2
				(Print 355 0)
				(aKen setCel: 0)
				(ego setMotion: MoveTo 269 165 self)
				(= cycles 22)
			)
			(3 (aKen setCycle: Fwd))
			(4
				(Print 355 1)
				(aKen setCel: 0)
				(ego view: 356 loop: 0 cel: 0 cycleSpeed: 1 setCycle: End)
				(= cycles 33)
			)
			(5
				(ego setCycle: Beg)
				(= cycles 11)
			)
			(6
				(ego loop: 1 cel: 0 setCycle: End)
				(= cycles 33)
			)
			(7
				(ego setCycle: Beg)
				(= cycles 11)
			)
			(8
				(ego
					view: global66
					cycleSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 161 165 self
				)
			)
			(9
				(ego setMotion: MoveTo 165 149 self)
			)
			(10
				(ego view: 356 loop: 2 cel: 0 setCycle: End self)
			)
			(11
				(ego
					cycleSpeed: 1
					setLoop: 3
					cel: 0
					setCycle: End
					setMotion: MoveTo 146 154 self
				)
			)
			(12
				(Print 355 2)
				(= seconds 2)
			)
			(13
				(aKen setCycle: Fwd)
				(= seconds 3)
			)
			(14
				(Print 355 3)
				(aKen viewer: tiradeCycler loop: 1)
				(= seconds 3)
			)
			(15
				(Print 355 4 #at -1 144)
				(= seconds 2)
			)
			(16
				(Print 355 5)
				(aKen loop: 2)
				(= seconds 3)
			)
			(17
				(Print 355 6)
				(AddViewToPic ego)
				(ego
					view: 355
					loop: 3
					posn: (aKen x?) (aKen y?)
					setPri: 5
					stopUpd:
				)
				(NormalActor aKen 3 352)
				(aKen
					illegalBits: 0
					viewer: 0
					posn: 30 140
					setPri: 3
					setMotion: MoveTo 30 132 self
				)
			)
			(18
				(aKen setMotion: MoveTo 40 128 self)
			)
			(19
				(Print 355 7 #at -1 144)
				(aKen setMotion: MoveTo 109 128 self)
			)
			(20
				(Print 355 8)
				(curRoom newRoom: 350)
			)
		)
	)
	
	(method (handleEvent event)
		(asm
			pushi    #handleEvent
			pushi    1
			lsp      event
			super    Script,  6
			not     
			bnt      code_0403
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			not     
			bnt      code_0403
			lag      modelessDialog
			bnt      code_0403
			pushi    #message
			pushi    0
			lap      event
			send     4
			push    
			ldi      13
			eq?     
			bnt      code_03e5
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      4
			eq?     
			bt       code_03f2
code_03e5:
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      1
			eq?     
			bnt      code_0403
code_03f2:
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			pushi    0
			callb    cls,  0
			pushi    #cue
			pushi    0
			self     4
code_0403:
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      1
			eq?     
			bnt      code_0428
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			push    
			ldi      3
			and     
			not     
			bnt      code_0428
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			bnt      code_0428
code_0428:
			ret     
		)
	)
)

(instance aFountain of Prop
	(properties
		y 104
		x 157
		view 350
		loop 3
	)
)

(instance aKen of Act
	(properties
		y 139
		x 33
		view 355
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: setPri: 10 stopUpd:)
	)
)

(instance tiradeCycler of Code
	(properties)
	
	(method (doit)
		(if (not (Random 0 4))
			(aKen cycleSpeed: (Random 0 2))
		)
	)
)

(instance atpComputer of PV
	(properties
		y 149
		x 33
		view 355
		loop 4
		priority 11
		signal $4000
	)
)

(instance atpPaper of PV
	(properties
		y 128
		x 67
		view 355
		loop 4
		cel 1
		priority 12
		signal $4000
	)
)

(instance atpPhone of PV
	(properties
		y 124
		x 57
		view 355
		loop 4
		cel 2
		priority 12
		signal $4000
	)
)

(instance atpSign of PV
	(properties
		y 142
		x 63
		view 355
		loop 4
		cel 3
		priority 12
		signal $4000
	)
)
