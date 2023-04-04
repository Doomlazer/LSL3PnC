;;; Sierra Script 1.0 - (do not remove this comment)
(script# 365)
(include sci.sh)
(use Main)
(use n021)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	rm365 0
)

(procedure (localproc_000c)
	(systemWindow color: 12 back: 8)
	(Print &rest #at 195 20 #title {Bambi says} #width 115)
	(systemWindow color: 0 back: 8)
)

(procedure (localproc_0042)
	(systemWindow color: 9 back: 8)
	(Print &rest #at 10 111 #title {You say} #width 150)
	(systemWindow color: 0 back: 8)
)

(instance rm365 of Rm
	(properties
		picture 365
	)
	
	(method (init)
		(Load rsVIEW 366)
		(Load rsVIEW 725)
		(Load rsPIC 366)
		(Load rsPIC 99)
		(Load rsSOUND 11)
		(Load rsSOUND 365)
		(Load rsSOUND 8)
		(Load rsSOUND 9)
		(Load rsSOUND 261)
		(super init:)
		(Bset 52)
		(HandsOff)
		(SL disable:)
		(TheMenuBar hide: state: 0)
		(aDoor init:)
		(aLid init:)
		(aBambi init:)
		(addToPics add: atpBed doit:)
		(self setScript: RoomScript)
		(ego
			view: 365
			loop: 3
			cel: 0
			setCycle: Walk
			illegalBits: 0
			posn: 115 112
			ignoreActors:
			init:
		)
		(User canInput: 0 canControl: 0 mapKeyToDir: 0)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (changeState newState)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0 (= seconds 3))
			(1 (aDoor setCycle: End self))
			(2
				(gTheMusic stop:)
				(orchidSeconds number: 11 loop: 1 play:)
				(aBambi cel: 1)
				(ego cel: 1)
				(aDoor stopUpd:)
				(= seconds 3)
			)
			(3
				(localproc_000c 365 0)
				(= seconds 3)
			)
			(4
				(aBambi
					view: 393
					setLoop: 1
					setMotion: MoveTo 180 110 self
				)
			)
			(5
				(aBambi setLoop: 4 cel: 0 stopUpd:)
				(= seconds 3)
			)
			(6
				(gTheMusic number: 8 loop: -1 play:)
				(localproc_000c 365 1)
				(= seconds 3)
			)
			(7
				(Print 365 2 #at -1 144)
				(= seconds 3)
			)
			(8
				(ego view: 725 setLoop: 0 setMotion: MoveTo 166 110 self)
			)
			(9
				(localproc_0042 365 3)
				(= seconds 3)
			)
			(10
				(aBambi hide:)
				(ego
					view: 366
					setLoop: 0
					posn: (aBambi x?) (- (aBambi y?) 20)
					setCycle: End self
				)
			)
			(11
				(ego
					viewer: egoHumpCycler
					posn: 201 94
					setLoop: 1
					setCycle: Fwd
				)
				(= seconds 7)
			)
			(12
				(localproc_000c 365 4)
				(= seconds 5)
			)
			(13
				(Print 365 5)
				(= cycles 11)
			)
			(14
				(orchidSeconds number: 365 loop: 1 play:)
				(aLid setCycle: End self)
			)
			(15
				(curRoom drawPic: 366 2)
				(aLid dispose:)
				(aDoor dispose:)
				(ego
					viewer: egoHumpCycler
					loop: 2
					setPri: 12
					cel: 0
					setCycle: Fwd
				)
				(aBambi
					posn: 187 75
					view: 366
					loop: 3
					setPri: 4
					cel: 0
					setCycle: Fwd
					show:
				)
				(gTheMusic number: 261 loop: -1 play:)
				(= seconds 5)
			)
			(16
				(localproc_000c 365 6)
				(= seconds 5)
			)
			(17
				(Print 365 7)
				(= seconds 3)
			)
			(18
				(localproc_0042 365 8)
				(= seconds 3)
			)
			(19
				(localproc_000c 365 9)
				(ego viewer: 0 stopUpd:)
				(aBambi stopUpd:)
				(= seconds 3)
			)
			(20
				(localproc_000c 365 10)
				(= seconds 3)
			)
			(21
				(localproc_0042 365 11)
				(= seconds 3)
			)
			(22
				(gTheMusic fade:)
				(curRoom drawPic: 99 6)
				(cast eachElementDo: #hide)
				(= seconds 3)
			)
			(23
				(Print 365 12)
				(gTheMusic number: 9 loop: 1 play:)
				(Print 365 13)
				(= seconds 3)
			)
			(24
				(systemWindow color: gameHours back: oldSysTime)
				(= gCurRoomNum 0)
				(curRoom newRoom: 360)
			)
		)
	)
)

(instance aBambi of Act
	(properties
		y 108
		x 124
		view 365
		loop 2
	)
	
	(method (init)
		(super init:)
		(self
			illegalBits: 0
			setPri: 7
			setCycle: Walk
			ignoreActors:
		)
	)
)

(instance aDoor of Prop
	(properties
		y 62
		x 118
		view 365
		loop 4
	)
	
	(method (init)
		(super init:)
		(self setPri: 2 ignoreActors:)
	)
)

(instance aLid of Prop
	(properties
		y 83
		x 235
		view 365
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 12 ignoreActors:)
	)
)

(instance atpBed of PV
	(properties
		y 118
		x 199
		view 365
		priority 4
		signal $4000
	)
)

(instance humpCycler of Code
	(properties)
	
	(method (doit &tmp temp0)
		(cond 
			((<= global88 2) (ego stopUpd:) (aBambi stopUpd:))
			((not (Random 0 9))
				(= temp0 (Random 0 5))
				(ego cycleSpeed: temp0)
				(aBambi cycleSpeed: temp0)
			)
		)
	)
)

(instance egoHumpCycler of Code
	(properties)
	
	(method (doit &tmp temp0)
		(cond 
			((<= global88 2) (ego stopUpd:) (aBambi stopUpd:))
			((not (Random 0 9))
				(= temp0 (Random 0 5))
				(ego cycleSpeed: temp0)
				(aBambi cycleSpeed: temp0)
			)
		)
	)
)
