;;; Sierra Script 1.0 - (do not remove this comment)
(script# 450)
(include sci.sh)
(use Main)
(use n021)
(use Intrface)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm450 0
)
(synonyms
	(blackboard blackboard blackboard blackboard blackboard special board awning)
	(tip tip buck cup jar tips)
	(buffet booth)
	(entertainer entertainer babe)
)

(local
	local0
	pattiPlayingPiano
	pianoMusic
	noEntryMsg
)
(instance rm450 of Rm
	(properties
		picture 450
		south 416
		west 416
	)
	
	(method (init)
		(Load rsVIEW 451)
		(super init:)
		(addToPics
			add: atpPiano
			add: atpChair1
			add: atpChair2
			add: atpChair3
			add: atpChair4
			add: atpChair5
			add:
				(if (and musicLoop (InRoom 18))
					atpBlackboard2
				else
					atpBlackboard1
				)
			doit:
		)
		(self setScript: RoomScript)
		(if (and musicLoop (InRoom 18)) (aMarker init:))
		(NormalEgo)
		(cond 
			((== gCurRoomNum 1)
				(aPatti
					view: 800
					init:
					loop: 1
					posn: 168 107
					setCycle: Walk
				)
				(ego ignoreActors: illegalBits: 0 posn: 139 121 view: 451)
				(RoomScript changeState: 10)
				(PattiScript changeState: 6)
				(HandsOff)
			)
			((== prevRoomNum 455)
				(= pattiPlayingPiano 1)
				(HandsOff)
				(ego ignoreActors: illegalBits: 0 posn: 139 121 view: 451)
				(RoomScript changeState: 10)
			)
			(else
				(if (and (== gameMinutes 6) (InRoom 12))
					(= pattiPlayingPiano 1)
					(if (== (Random 0 3) 3) (aRoger init:))
					(if (== (Random 0 3) 3) (aElvis init:))
				)
				(if (> (ego y?) 130)
					(ego posn: 29 188)
				else
					(ego posn: 9 168)
				)
			)
		)
		(ego init:)
		(if pattiPlayingPiano
			(aPatti init:)
			(PattiScript changeState: 1)
			(blockPatti init:)
			(if (not (Btst 67)) (aTips init:))
			(ego observeBlocks: blockPatti observeControl: 16384)
		)
		(if (and (not (Btst 67)) musicLoop) (aTips init:))
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) $0200)
			(if (not noEntryMsg)
				(= noEntryMsg 1)
				(ego
					posn: (ego xLast?) (ego yLast?)
					setMotion: 0
					observeControl: 512
					forceUpd:
				)
				(if musicLoop (Print 450 0) else (Print 450 1))
			)
		else
			(= noEntryMsg 0)
		)
		(if pattiPlayingPiano
			(ego observeBlocks: blockPatti observeControl: 16384)
		)
	)
	
	(method (changeState newState)
		(ChangeScriptState self newState 1 1)
		(switch (= state newState)
			(0)
			(6
				(Ok)
				(ego ignoreActors: illegalBits: 0)
				(cond 
					((& (ego onControl:) $0002) (self changeState: 8))
					((& (ego onControl:) $0020) (self changeState: 7))
					(else (ego setMotion: MoveTo 118 (ego y?) self))
				)
				(theGame setCursor: 998 (HaveMouse))
				(= theCursor 998)
			)
			(7
				(ego setMotion: MoveTo (ego x?) 113 self)
			)
			(8
				(ego setMotion: MoveTo 134 113 self)
			)
			(9
				(ego
					posn: 139 121
					view: 451
					loop: 0
					cel: 0
					setCycle: End self
				)
			)
			(10
				(if (!= gCurRoomNum 1)
					(User canInput: 1)
					(= gCurRoomNum 1004)
				)
				(ego
					loop: (Random 1 2)
					cel: 0
					cycleSpeed: (Random 0 2)
					setCycle: End
				)
				(-- state)
				(= seconds (Random 3 6))
			)
			(11
				(Ok)
				(HandsOff)
				(= seconds (= cycles 0))
				(ego setLoop: 0 setCel: 255 setCycle: Beg self)
			)
			(12
				(ego posn: 134 113)
				(NormalEgo 3)
				(= gCurRoomNum 0)
			)
			(13
				(HandsOff)
				(Ok)
				(= gCurRoomNum 14)
				(if (not (Btst 9))
					(Printf 450 48 global171)
					(= seconds 3)
				else
					(self cue:)
				)
			)
			(14
				(if (not (Btst 9))
					(Bset 9)
					(theGame changeScore: 5)
					(Print 450 49)
				)
				(curRoom newRoom: 455)
			)
		)
	)
	
	(method (handleEvent event)
		(asm
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			bnt      code_04a1
			ret     
code_04a1:
			pushi    1
			lofss    'lie'
			callk    Said,  2
			bnt      code_0553
			lsg      gCurRoomNum
			ldi      1004
			eq?     
			bnt      code_04ba
			pushi    0
			callb    YouAre,  0
			jmp      code_0550
code_04ba:
			lsg      gCurRoomNum
			ldi      0
			ne?     
			bnt      code_04c8
			pushi    0
			callb    GoodIdea,  0
			jmp      code_0550
code_04c8:
			lag      musicLoop
			bnt      code_04d9
			pushi    2
			pushi    450
			pushi    2
			calle    Print,  4
			jmp      code_0550
code_04d9:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      4
			and     
			bnt      code_04f5
			pushi    2
			pushi    450
			pushi    3
			calle    Print,  4
			jmp      code_0550
code_04f5:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      2
			and     
			bt       code_051f
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      32
			and     
			bt       code_051f
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      16
			and     
			bnt      code_0528
code_051f:
			pushi    #changeState
			pushi    1
			pushi    6
			self     6
			jmp      code_0550
code_0528:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      64
			and     
			bnt      code_0544
			pushi    2
			pushi    450
			pushi    4
			calle    Print,  4
			jmp      code_0550
code_0544:
			pushi    2
			pushi    450
			pushi    5
			calle    Print,  4
code_0550:
			jmp      code_1242
code_0553:
			pushi    1
			lofss    'nightstand,(get<off),(get<up),(nightstand<up)'
			callk    Said,  2
			bt       code_0565
			pushi    1
			lofss    'exit/barstool,barstool'
			callk    Said,  2
			bnt      code_058a
code_0565:
			lsg      gCurRoomNum
			ldi      0
			eq?     
			bnt      code_0572
			pushi    0
			callb    YouAre,  0
			jmp      code_0587
code_0572:
			lsg      gCurRoomNum
			ldi      1004
			ne?     
			bnt      code_0580
			pushi    0
			callb    GoodIdea,  0
			jmp      code_0587
code_0580:
			pushi    #changeState
			pushi    1
			pushi    11
			self     6
code_0587:
			jmp      code_1242
code_058a:
			pushi    1
			lofss    'get/marker'
			callk    Said,  2
			bnt      code_060d
			lsg      gCurRoomNum
			ldi      0
			ne?     
			bnt      code_05a0
			pushi    0
			callb    GoodIdea,  0
			jmp      code_060a
code_05a0:
			lag      musicLoop
			not     
			bnt      code_05b3
			pushi    2
			pushi    450
			pushi    6
			calle    Print,  4
			jmp      code_060a
code_05b3:
			pushi    1
			pushi    18
			callb    InRoom,  2
			not     
			bnt      code_05c2
			pushi    0
			callb    AlreadyTook,  0
			jmp      code_060a
code_05c2:
			pushi    #inRect
			pushi    4
			pushi    10
			pushi    152
			pushi    55
			pushi    160
			lag      ego
			send     12
			not     
			bnt      code_05de
			pushi    0
			callb    NotClose,  0
			jmp      code_060a
code_05de:
			pushi    0
			callb    Ok,  0
			pushi    #get
			pushi    1
			pushi    18
			lag      ego
			send     6
			pushi    #changeScore
			pushi    1
			pushi    50
			lag      theGame
			send     6
			pushi    #dispose
			pushi    0
			lofsa    aMarker
			send     4
			pushi    2
			pushi    450
			pushi    7
			calle    Print,  4
code_060a:
			jmp      code_1242
code_060d:
			pushi    1
			lofss    'get/tip'
			callk    Said,  2
			bnt      code_06c7
			lsg      gCurRoomNum
			ldi      0
			ne?     
			bnt      code_0625
			pushi    0
			callb    GoodIdea,  0
			jmp      code_06c4
code_0625:
			lag      musicLoop
			not     
			bnt      code_0639
			pushi    2
			pushi    450
			pushi    8
			calle    Print,  4
			jmp      code_06c4
code_0639:
			pushi    1
			pushi    67
			callb    Btst,  2
			not     
			bnt      code_0648
			pushi    0
			callb    AlreadyTook,  0
			jmp      code_06c4
code_0648:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      2
			and     
			not     
			bnt      code_0665
			pushi    2
			pushi    450
			pushi    9
			calle    Print,  4
			jmp      code_06c4
code_0665:
			pushi    0
			callb    Ok,  0
			pushi    #get
			pushi    1
			pushi    6
			lag      ego
			send     6
			pushi    #view
			pushi    1
			pushi    25
			pushi    #at
			pushi    1
			pushi    6
			class    Inv
			send     6
			send     6
			pushi    3
			pushi    #name
			pushi    0
			pushi    #at
			pushi    1
			pushi    6
			class    Inv
			send     6
			send     4
			push    
			pushi    450
			pushi    10
			callk    Format,  6
			pushi    #changeScore
			pushi    1
			pushi    25
			lag      theGame
			send     6
			ldi      43
			sag      global94
			pushi    1
			pushi    67
			callb    Bclr,  2
			pushi    #dispose
			pushi    0
			lofsa    aTips
			send     4
			pushi    2
			pushi    450
			pushi    11
			calle    Print,  4
code_06c4:
			jmp      code_1242
code_06c7:
			pushi    1
			lofss    'get,buy,call/attendant,attendant,drink'
			callk    Said,  2
			bnt      code_06df
			pushi    2
			pushi    450
			pushi    12
			calle    Print,  4
			jmp      code_1242
code_06df:
			pushi    1
			lofss    '/attendant,attendant'
			callk    Said,  2
			bnt      code_06f7
			pushi    2
			pushi    450
			pushi    13
			calle    Print,  4
			jmp      code_1242
code_06f7:
			pushi    1
			lofss    'give,backdrop/tip'
			callk    Said,  2
			bt       code_0709
			pushi    1
			lofss    'tip'
			callk    Said,  2
			bnt      code_0753
code_0709:
			lag      musicLoop
			bnt      code_071b
			pushi    2
			pushi    450
			pushi    14
			calle    Print,  4
			jmp      code_0750
code_071b:
			pushi    #has
			pushi    1
			pushi    6
			lag      ego
			send     6
			not     
			bnt      code_072e
			pushi    0
			callb    DontHave,  0
			jmp      code_0750
code_072e:
			pushi    1
			pushi    67
			callb    Btst,  2
			bnt      code_0744
			pushi    2
			pushi    450
			pushi    15
			calle    Print,  4
			jmp      code_0750
code_0744:
			pushi    2
			pushi    450
			pushi    16
			calle    Print,  4
code_0750:
			jmp      code_1242
code_0753:
			pushi    1
			lofss    'gamble/keyboard'
			callk    Said,  2
			bnt      code_07c8
			lag      musicLoop
			bnt      code_076e
			pushi    2
			pushi    450
			pushi    17
			calle    Print,  4
			jmp      code_07c5
code_076e:
			pushi    #contains
			pushi    1
			lofss    aPatti
			lag      cast
			send     6
			not     
			bnt      code_07a4
			pushi    2
			pushi    450
			pushi    18
			calle    Print,  4
			lsg      global88
			ldi      4
			eq?     
			bnt      code_07a2
			pushi    5
			pushi    450
			pushi    19
			pushi    67
			pushi    65535
			pushi    144
			calle    Print,  10
code_07a2:
			jmp      code_07c5
code_07a4:
			pushi    2
			pushi    450
			pushi    20
			calle    Print,  4
			pushi    2
			pushi    450
			pushi    21
			calle    Print,  4
			pushi    #changeState
			pushi    1
			pushi    1
			lofsa    PattiScript
			send     6
code_07c5:
			jmp      code_1242
code_07c8:
			pushi    1
			lofss    'hear'
			callk    Said,  2
			bnt      code_07f2
			lal      pattiPlayingPiano
			bnt      code_07e3
			pushi    2
			pushi    450
			pushi    22
			calle    Print,  4
			jmp      code_07ef
code_07e3:
			pushi    2
			pushi    450
			pushi    23
			calle    Print,  4
code_07ef:
			jmp      code_1242
code_07f2:
			pushi    1
			lofss    'look<below'
			callk    Said,  2
			bnt      code_080a
			pushi    2
			pushi    450
			pushi    24
			calle    Print,  4
			jmp      code_1242
code_080a:
			pushi    1
			lofss    'look>'
			callk    Said,  2
			bnt      code_0a18
			pushi    1
			lofss    '/blackboard'
			callk    Said,  2
			bt       code_082e
			pushi    1
			pushi    18
			callb    InRoom,  2
			bnt      code_0867
			pushi    1
			lofss    '/marker'
			callk    Said,  2
			bnt      code_0867
code_082e:
			lag      musicLoop
			not     
			bnt      code_0841
			pushi    2
			pushi    450
			pushi    25
			calle    Print,  4
			jmp      code_0864
code_0841:
			pushi    1
			pushi    18
			callb    InRoom,  2
			not     
			bnt      code_0858
			pushi    2
			pushi    450
			pushi    26
			calle    Print,  4
			jmp      code_0864
code_0858:
			pushi    2
			pushi    450
			pushi    27
			calle    Print,  4
code_0864:
			jmp      code_0a15
code_0867:
			pushi    1
			lofss    '/wall,ceiling,burn'
			callk    Said,  2
			bnt      code_087f
			pushi    2
			pushi    450
			pushi    28
			calle    Print,  4
			jmp      code_0a15
code_087f:
			pushi    1
			lofss    '/bar'
			callk    Said,  2
			bnt      code_08bd
			lal      pattiPlayingPiano
			bnt      code_089a
			pushi    2
			pushi    450
			pushi    29
			calle    Print,  4
			jmp      code_08ba
code_089a:
			pushi    2
			pushi    450
			pushi    30
			calle    Print,  4
			pushi    5
			pushi    450
			pushi    31
			pushi    67
			pushi    65535
			pushi    144
			calle    Print,  10
code_08ba:
			jmp      code_0a15
code_08bd:
			pushi    1
			lofss    '/buffet'
			callk    Said,  2
			bnt      code_08e9
			pushi    2
			pushi    450
			pushi    32
			calle    Print,  4
			pushi    5
			pushi    450
			pushi    33
			pushi    67
			pushi    65535
			pushi    144
			calle    Print,  10
			jmp      code_0a15
code_08e9:
			pushi    1
			lofss    '/barstool'
			callk    Said,  2
			bnt      code_0901
			pushi    2
			pushi    450
			pushi    34
			calle    Print,  4
			jmp      code_0a15
code_0901:
			pushi    1
			lofss    '/drink'
			callk    Said,  2
			bnt      code_0919
			pushi    2
			pushi    450
			pushi    35
			calle    Print,  4
			jmp      code_0a15
code_0919:
			pushi    1
			pushi    67
			callb    Btst,  2
			bnt      code_0939
			pushi    1
			lofss    '/tip'
			callk    Said,  2
			bnt      code_0939
			pushi    2
			pushi    450
			pushi    36
			calle    Print,  4
			jmp      code_0a15
code_0939:
			pushi    1
			lofss    '/door'
			callk    Said,  2
			bnt      code_0951
			pushi    2
			pushi    450
			pushi    37
			calle    Print,  4
			jmp      code_0a15
code_0951:
			pushi    1
			lofss    '/keyboard,entertainer'
			callk    Said,  2
			bnt      code_09d0
			lsg      gameMinutes
			ldi      6
			lt?     
			bnt      code_096f
			pushi    2
			pushi    450
			pushi    38
			calle    Print,  4
			jmp      code_09ce
code_096f:
			pushi    1
			pushi    12
			callb    InRoom,  2
			bnt      code_0999
			pushi    2
			pushi    450
			pushi    39
			calle    Print,  4
			pushi    5
			pushi    450
			pushi    40
			pushi    67
			pushi    65535
			pushi    144
			calle    Print,  10
			jmp      code_09ce
code_0999:
			lag      musicLoop
			not     
			bnt      code_09ac
			pushi    2
			pushi    450
			pushi    41
			calle    Print,  4
			jmp      code_09ce
code_09ac:
			pushi    1
			pushi    67
			callb    Btst,  2
			bnt      code_09c2
			pushi    2
			pushi    450
			pushi    42
			calle    Print,  4
			jmp      code_09ce
code_09c2:
			pushi    2
			pushi    450
			pushi    43
			calle    Print,  4
code_09ce:
			jmp      code_0a15
code_09d0:
			pushi    1
			lofss    '[/bar,area]'
			callk    Said,  2
			bnt      code_0a15
			lag      musicLoop
			bnt      code_09eb
			pushi    2
			pushi    450
			pushi    44
			calle    Print,  4
			jmp      code_0a15
code_09eb:
			pushi    2
			pushi    450
			pushi    45
			calle    Print,  4
			lal      pattiPlayingPiano
			bnt      code_0a09
			pushi    2
			pushi    450
			pushi    46
			calle    Print,  4
			jmp      code_0a15
code_0a09:
			pushi    2
			pushi    450
			pushi    47
			calle    Print,  4
code_0a15:
			jmp      code_1242
code_0a18:
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      1
			eq?     
			bnt      code_1240
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			push    
			ldi      3
			and     
			not     
			bnt      code_1240
			pushi    3
			lofss    aElvis
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			callb    proc0_26,  6
			bnt      code_0aa2
			pushi    #contains
			pushi    1
			lofss    aElvis
			lag      cast
			send     6
			bnt      code_0aa2
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			lsg      theCursor
			dup     
			ldi      998
			eq?     
			bnt      code_0a7a
			pushi    2
			pushi    450
			pushi    50
			calle    Print,  4
			jmp      code_0aa1
code_0a7a:
			dup     
			ldi      996
			eq?     
			bnt      code_0a99
			pushi    2
			pushi    450
			pushi    52
			calle    Print,  4
			pushi    #changeState
			pushi    1
			pushi    3
			lofsa    ElvisScript
			send     6
			jmp      code_0aa1
code_0a99:
			pushi    #claimed
			pushi    1
			pushi    0
			lap      event
			send     6
code_0aa1:
			toss    
code_0aa2:
			pushi    3
			lofss    atpBlackboard1
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			callb    proc0_26,  6
			bnt      code_0b0e
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			lsg      theCursor
			dup     
			ldi      998
			eq?     
			bnt      code_0b05
			lag      musicLoop
			not     
			bnt      code_0ae0
			pushi    2
			pushi    450
			pushi    25
			calle    Print,  4
			jmp      code_0b03
code_0ae0:
			pushi    1
			pushi    18
			callb    InRoom,  2
			not     
			bnt      code_0af7
			pushi    2
			pushi    450
			pushi    26
			calle    Print,  4
			jmp      code_0b03
code_0af7:
			pushi    2
			pushi    450
			pushi    27
			calle    Print,  4
code_0b03:
			jmp      code_0b0d
code_0b05:
			pushi    #claimed
			pushi    1
			pushi    0
			lap      event
			send     6
code_0b0d:
			toss    
code_0b0e:
			pushi    3
			lofss    atpChair1
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			callb    proc0_26,  6
			bt       code_0b91
			pushi    3
			lofss    atpChair2
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			callb    proc0_26,  6
			bt       code_0b91
			pushi    3
			lofss    atpChair3
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			callb    proc0_26,  6
			bt       code_0b91
			pushi    3
			lofss    atpChair4
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			callb    proc0_26,  6
			bt       code_0b91
			pushi    3
			lofss    atpChair5
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			callb    proc0_26,  6
			bnt      code_0c56
code_0b91:
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			lsg      theCursor
			dup     
			ldi      995
			eq?     
			bnt      code_0c4d
			lsg      gCurRoomNum
			ldi      1004
			eq?     
			bnt      code_0bb2
			pushi    0
			callb    YouAre,  0
			jmp      code_0c4b
code_0bb2:
			lsg      gCurRoomNum
			ldi      0
			ne?     
			bnt      code_0bc0
			pushi    0
			callb    GoodIdea,  0
			jmp      code_0c4b
code_0bc0:
			lag      musicLoop
			bnt      code_0bd1
			pushi    2
			pushi    450
			pushi    2
			calle    Print,  4
			jmp      code_0c4b
code_0bd1:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      4
			and     
			bnt      code_0bed
			pushi    2
			pushi    450
			pushi    3
			calle    Print,  4
			jmp      code_0c4b
code_0bed:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      2
			and     
			bt       code_0c17
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      32
			and     
			bt       code_0c17
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      16
			and     
			bnt      code_0c23
code_0c17:
			pushi    #changeState
			pushi    1
			pushi    6
			lofsa    RoomScript
			send     6
			jmp      code_0c4b
code_0c23:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      64
			and     
			bnt      code_0c3f
			pushi    2
			pushi    450
			pushi    4
			calle    Print,  4
			jmp      code_0c4b
code_0c3f:
			pushi    2
			pushi    450
			pushi    5
			calle    Print,  4
code_0c4b:
			jmp      code_0c55
code_0c4d:
			pushi    #claimed
			pushi    1
			pushi    0
			lap      event
			send     6
code_0c55:
			toss    
code_0c56:
			pushi    3
			lofss    aRoger
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			callb    proc0_26,  6
			bnt      code_0cc5
			pushi    #contains
			pushi    1
			lofss    aRoger
			lag      cast
			send     6
			bnt      code_0cc5
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			lsg      theCursor
			dup     
			ldi      998
			eq?     
			bnt      code_0c9b
			pushi    2
			pushi    450
			pushi    53
			calle    Print,  4
			jmp      code_0cc4
code_0c9b:
			dup     
			ldi      996
			eq?     
			bnt      code_0cbc
			pushi    2
			pushi    450
			pushi    54
			calle    Print,  4
			pushi    2
			pushi    450
			pushi    55
			calle    Print,  4
			jmp      code_0cc4
code_0cbc:
			pushi    #claimed
			pushi    1
			pushi    0
			lap      event
			send     6
code_0cc4:
			toss    
code_0cc5:
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      1
			eq?     
			bnt      code_0d4e
			lsg      gCurRoomNum
			ldi      1004
			eq?     
			bnt      code_0d4e
			lsg      theCursor
			ldi      992
			eq?     
			bt       code_0cfa
			lsg      theCursor
			ldi      999
			eq?     
			bt       code_0cfa
			lsg      theCursor
			ldi      991
			eq?     
			bt       code_0cfa
			lsg      theCursor
			lag      gTheCursor
			eq?     
			bnt      code_0d4e
code_0cfa:
			pushi    #changeState
			pushi    1
			pushi    11
			self     6
			pushi    #setCursor
			pushi    2
			pushi    998
			pushi    0
			callk    HaveMouse,  0
			push    
			lag      theGame
			send     8
			ldi      998
			sag      theCursor
			lsg      theCursor
			ldi      993
			eq?     
			bnt      code_0d4e
			lsg      theCursor
			ldi      994
			eq?     
			bnt      code_0d4e
			lsg      theCursor
			ldi      995
			eq?     
			bnt      code_0d4e
			lsg      theCursor
			ldi      996
			eq?     
			bnt      code_0d4e
			lsg      theCursor
			ldi      997
			eq?     
			bnt      code_0d4e
			lsg      theCursor
			ldi      998
			eq?     
			bnt      code_0d4e
			lsg      theCursor
			ldi      999
			eq?     
			bnt      code_0d4e
code_0d4e:
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			ldi      1
			gt?     
			bnt      code_0db1
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			ldi      57
			lt?     
			bnt      code_0db1
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			ldi      172
			gt?     
			bnt      code_0db1
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			ldi      189
			lt?     
			bnt      code_0db1
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			lsg      theCursor
			dup     
			ldi      999
			eq?     
			bnt      code_0da8
			pushi    #setMotion
			pushi    3
			class    MoveTo
			push    
			pushi    16
			pushi    192
			lag      ego
			send     10
			jmp      code_0db0
code_0da8:
			pushi    #claimed
			pushi    1
			pushi    0
			lap      event
			send     6
code_0db0:
			toss    
code_0db1:
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			ldi      24
			gt?     
			bnt      code_0ed5
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			ldi      45
			lt?     
			bnt      code_0ed5
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			ldi      128
			gt?     
			bnt      code_0ed5
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			ldi      157
			lt?     
			bnt      code_0ed5
			pushi    #contains
			pushi    1
			lofss    aMarker
			lag      cast
			send     6
			bnt      code_0ed5
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			lsg      theCursor
			dup     
			ldi      998
			eq?     
			bnt      code_0e4c
			pushi    1
			pushi    18
			callb    InRoom,  2
			bnt      code_0e49
			lag      musicLoop
			not     
			bnt      code_0e24
			pushi    2
			pushi    450
			pushi    25
			calle    Print,  4
			jmp      code_0e47
code_0e24:
			pushi    1
			pushi    18
			callb    InRoom,  2
			not     
			bnt      code_0e3b
			pushi    2
			pushi    450
			pushi    26
			calle    Print,  4
			jmp      code_0e47
code_0e3b:
			pushi    2
			pushi    450
			pushi    27
			calle    Print,  4
code_0e47:
			bnt      code_0e49
code_0e49:
			jmp      code_0ed4
code_0e4c:
			dup     
			ldi      995
			eq?     
			bnt      code_0ecc
			lsg      gCurRoomNum
			ldi      0
			ne?     
			bnt      code_0e60
			pushi    0
			callb    GoodIdea,  0
			jmp      code_0eca
code_0e60:
			lag      musicLoop
			not     
			bnt      code_0e73
			pushi    2
			pushi    450
			pushi    6
			calle    Print,  4
			jmp      code_0eca
code_0e73:
			pushi    1
			pushi    18
			callb    InRoom,  2
			not     
			bnt      code_0e82
			pushi    0
			callb    AlreadyTook,  0
			jmp      code_0eca
code_0e82:
			pushi    #inRect
			pushi    4
			pushi    10
			pushi    152
			pushi    55
			pushi    160
			lag      ego
			send     12
			not     
			bnt      code_0e9e
			pushi    0
			callb    NotClose,  0
			jmp      code_0eca
code_0e9e:
			pushi    0
			callb    Ok,  0
			pushi    #get
			pushi    1
			pushi    18
			lag      ego
			send     6
			pushi    #changeScore
			pushi    1
			pushi    50
			lag      theGame
			send     6
			pushi    #dispose
			pushi    0
			lofsa    aMarker
			send     4
			pushi    2
			pushi    450
			pushi    7
			calle    Print,  4
code_0eca:
			jmp      code_0ed4
code_0ecc:
			pushi    #claimed
			pushi    1
			pushi    0
			lap      event
			send     6
code_0ed4:
			toss    
code_0ed5:
			pushi    3
			lofss    aTips
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			callb    proc0_26,  6
			bnt      code_101e
			pushi    #contains
			pushi    1
			lofss    aTips
			lag      cast
			send     6
			bnt      code_101e
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			lsg      theCursor
			dup     
			ldi      998
			eq?     
			bnt      code_0f58
			lag      musicLoop
			bnt      code_0f20
			pushi    2
			pushi    450
			pushi    14
			calle    Print,  4
			jmp      code_0f55
code_0f20:
			pushi    #has
			pushi    1
			pushi    6
			lag      ego
			send     6
			not     
			bnt      code_0f33
			pushi    0
			callb    DontHave,  0
			jmp      code_0f55
code_0f33:
			pushi    1
			pushi    67
			callb    Btst,  2
			bnt      code_0f49
			pushi    2
			pushi    450
			pushi    15
			calle    Print,  4
			jmp      code_0f55
code_0f49:
			pushi    2
			pushi    450
			pushi    16
			calle    Print,  4
code_0f55:
			jmp      code_101d
code_0f58:
			dup     
			ldi      995
			eq?     
			bnt      code_1015
			lsg      gCurRoomNum
			ldi      0
			ne?     
			bnt      code_0f6e
			pushi    0
			callb    GoodIdea,  0
			jmp      code_1013
code_0f6e:
			lag      musicLoop
			not     
			bnt      code_0f82
			pushi    2
			pushi    450
			pushi    8
			calle    Print,  4
			jmp      code_1013
code_0f82:
			pushi    1
			pushi    67
			callb    Btst,  2
			bnt      code_0f8e
			pushi    0
			callb    AlreadyTook,  0
code_0f8e:
			bnt      code_0f93
			jmp      code_1013
code_0f93:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      2
			and     
			not     
			bnt      code_0fb4
			pushi    2
			pushi    450
			pushi    9
			calle    Print,  4
			pushi    0
			callb    NotClose,  0
			jmp      code_1013
code_0fb4:
			pushi    0
			callb    Ok,  0
			pushi    #get
			pushi    1
			pushi    6
			lag      ego
			send     6
			pushi    #view
			pushi    1
			pushi    25
			pushi    #at
			pushi    1
			pushi    6
			class    Inv
			send     6
			send     6
			pushi    3
			pushi    #name
			pushi    0
			pushi    #at
			pushi    1
			pushi    6
			class    Inv
			send     6
			send     4
			push    
			pushi    450
			pushi    10
			callk    Format,  6
			pushi    #changeScore
			pushi    1
			pushi    25
			lag      theGame
			send     6
			ldi      43
			sag      global94
			pushi    1
			pushi    67
			callb    Bset,  2
			pushi    #dispose
			pushi    0
			lofsa    aTips
			send     4
			pushi    2
			pushi    450
			pushi    11
			calle    Print,  4
code_1013:
			jmp      code_101d
code_1015:
			pushi    #claimed
			pushi    1
			pushi    0
			lap      event
			send     6
code_101d:
			toss    
code_101e:
			pushi    3
			lofss    aPatti
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			callb    proc0_26,  6
			bnt      code_1240
			pushi    #contains
			pushi    1
			lofss    aPatti
			lag      cast
			send     6
			bnt      code_1240
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			lsg      theCursor
			dup     
			ldi      998
			eq?     
			bnt      code_107a
			lsg      gCurRoomNum
			ldi      1004
			ne?     
			bnt      code_106d
			pushi    2
			pushi    450
			pushi    59
			calle    Print,  4
			jmp      code_1077
code_106d:
			pushi    #changeState
			pushi    1
			pushi    13
			lofsa    RoomScript
			send     6
code_1077:
			jmp      code_123f
code_107a:
			dup     
			ldi      995
			eq?     
			bnt      code_1210
			pushi    #fade
			pushi    0
			lag      gTheMusic
			send     4
			pushi    2
			pushi    1
			pushi    16
			callk    Random,  4
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_109d
			ldi      110
			jmp      code_113b
code_109d:
			dup     
			ldi      2
			eq?     
			bnt      code_10a8
			ldi      120
			jmp      code_113b
code_10a8:
			dup     
			ldi      3
			eq?     
			bnt      code_10b4
			ldi      206
			jmp      code_113b
code_10b4:
			dup     
			ldi      4
			eq?     
			bnt      code_10bf
			ldi      265
			jmp      code_113b
code_10bf:
			dup     
			ldi      5
			eq?     
			bnt      code_10ca
			ldi      323
			jmp      code_113b
code_10ca:
			dup     
			ldi      6
			eq?     
			bnt      code_10d5
			ldi      330
			jmp      code_113b
code_10d5:
			dup     
			ldi      7
			eq?     
			bnt      code_10e0
			ldi      335
			jmp      code_113b
code_10e0:
			dup     
			ldi      8
			eq?     
			bnt      code_10eb
			ldi      395
			jmp      code_113b
code_10eb:
			dup     
			ldi      9
			eq?     
			bnt      code_10f6
			ldi      399
			jmp      code_113b
code_10f6:
			dup     
			ldi      0
			eq?     
			bnt      code_1101
			ldi      431
			jmp      code_113b
code_1101:
			dup     
			ldi      11
			eq?     
			bnt      code_110c
			ldi      435
			jmp      code_113b
code_110c:
			dup     
			ldi      12
			eq?     
			bnt      code_1117
			ldi      500
			jmp      code_113b
code_1117:
			dup     
			ldi      13
			eq?     
			bnt      code_1122
			ldi      560
			jmp      code_113b
code_1122:
			dup     
			ldi      14
			eq?     
			bnt      code_112d
			ldi      599
			jmp      code_113b
code_112d:
			dup     
			ldi      15
			eq?     
			bnt      code_1138
			ldi      540
			jmp      code_113b
code_1138:
			ldi      261
code_113b:
			toss    
			sal      pianoMusic
			pushi    3
			pushi    450
			pushi    56
			lsl      pianoMusic
			dup     
			ldi      110
			eq?     
			bnt      code_1153
			lofsa    {Sierra}
			jmp      code_1200
code_1153:
			dup     
			ldi      120
			eq?     
			bnt      code_115f
			lofsa    {Title}
			jmp      code_1200
code_115f:
			dup     
			ldi      206
			eq?     
			bnt      code_116c
			lofsa    {Binocular}
			jmp      code_1200
code_116c:
			dup     
			ldi      265
			eq?     
			bnt      code_1179
			lofsa    {Tawni's}
			jmp      code_1200
code_1179:
			dup     
			ldi      323
			eq?     
			bnt      code_1185
			lofsa    {Dewey, Cheatem and Howe}
			jmp      code_1200
code_1185:
			dup     
			ldi      330
			eq?     
			bnt      code_1191
			lofsa    {Dale Exotic Dance}
			jmp      code_1200
code_1191:
			dup     
			ldi      335
			eq?     
			bnt      code_119d
			lofsa    {Dale's}
			jmp      code_1200
code_119d:
			dup     
			ldi      395
			eq?     
			bnt      code_11a9
			lofsa    {Bambi's}
			jmp      code_1200
code_11a9:
			dup     
			ldi      399
			eq?     
			bnt      code_11b5
			lofsa    {Fat City}
			jmp      code_1200
code_11b5:
			dup     
			ldi      431
			eq?     
			bnt      code_11c1
			lofsa    {Cherri's Dance}
			jmp      code_1200
code_11c1:
			dup     
			ldi      435
			eq?     
			bnt      code_11cd
			lofsa    {Cherri's}
			jmp      code_1200
code_11cd:
			dup     
			ldi      500
			eq?     
			bnt      code_11d9
			lofsa    {Bamboo}
			jmp      code_1200
code_11d9:
			dup     
			ldi      560
			eq?     
			bnt      code_11e5
			lofsa    {Whitewater Rafting}
			jmp      code_1200
code_11e5:
			dup     
			ldi      599
			eq?     
			bnt      code_11f1
			lofsa    {Nontoonyt Jungle}
			jmp      code_1200
code_11f1:
			dup     
			ldi      540
			eq?     
			bnt      code_11fd
			lofsa    {Feral Pig}
			jmp      code_1200
code_11fd:
			lofsa    {Larry Gets Crabs}
code_1200:
			toss    
			push    
			calle    Printf,  6
			pushi    #changeState
			pushi    1
			pushi    1
			self     6
			jmp      code_123f
code_1210:
			dup     
			ldi      996
			eq?     
			bnt      code_1237
			lag      musicLoop
			bnt      code_1229
			pushi    2
			pushi    450
			pushi    57
			calle    Print,  4
			jmp      code_1235
code_1229:
			pushi    2
			pushi    450
			pushi    58
			calle    Print,  4
code_1235:
			jmp      code_123f
code_1237:
			pushi    #claimed
			pushi    1
			pushi    0
			lap      event
			send     6
code_123f:
			toss    
code_1240:
			bnt      code_1242
code_1242:
			ret     
		)
	)
)

(instance aElvis of Prop
	(properties
		y 145
		x 150
		view 453
		loop 2
	)
	
	(method (init)
		(super init:)
		(self setScript: ElvisScript)
	)
)

(instance ElvisScript of Script
	(properties)
	
	(method (changeState newState)
		(ChangeScriptState self newState 3 3)
		(switch (= state newState)
			(0
				(aElvis loop: 2 setCel: 0)
				(= cycles (Random 22 111))
			)
			(1
				(if (not (Random 0 3))
					(aElvis loop: (Random 2 3) cel: 0 setCycle: End)
				else
					(= state -1)
				)
				(= cycles (Random 22 55))
			)
			(2
				(aElvis setCycle: Beg self)
				(= state -1)
			)
			(3
				(aElvis loop: 4 cel: 0 setCycle: End)
				(= cycles 22)
				(= state 1)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(cond 
			((Said 'look/man,elvis') (Print 450 50))
			((Said 'address/elvis') (Print 450 51))
			((Said '/elvis') (Print 450 52) (self changeState: 3))
		)
	)
)

(instance aRoger of Prop
	(properties
		y 133
		x 144
		view 453
	)
	
	(method (init)
		(super init:)
		(self setScript: RogerScript)
	)
)

(instance RogerScript of Script
	(properties)
	
	(method (changeState newState)
		(ChangeScriptState self newState 4 4)
		(switch (= state newState)
			(0
				(aRoger loop: 0 setCel: 0)
				(= seconds (Random 5 10))
			)
			(1
				(if (not (Random 0 2))
					(aRoger loop: (Random 0 1) setCycle: End)
				else
					(= state -1)
				)
				(= cycles (Random 22 55))
			)
			(2
				(aRoger setCycle: Beg self)
				(= state -1)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(cond 
			((Said 'look/man,hardy') (Print 450 53))
			((Said 'address/hardy,man') (Print 450 51))
			((Said '/hardy') (Print 450 54) (Print 450 55))
		)
	)
)

(instance atpPiano of PV
	(properties
		y 116
		x 143
		view 450
		priority 8
		signal $4000
	)
)

(instance aTips of View
	(properties
		y 91
		x 150
		view 450
		cel 2
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: setPri: 9 stopUpd:)
	)
)

(instance atpBlackboard1 of PV
	(properties
		y 153
		x 36
		view 450
		cel 3
		priority 10
	)
)

(instance atpBlackboard2 of PV
	(properties
		y 153
		x 36
		view 450
		cel 4
		priority 10
	)
)

(instance aMarker of Prop
	(properties
		y 145
		x 29
		view 450
		loop 1
		cycleSpeed 2
	)
	
	(method (init)
		(super init:)
		(self setPri: 11 setCycle: Fwd)
	)
)

(instance atpChair1 of PV
	(properties
		y 122
		x 137
		view 450
		cel 1
		priority 8
	)
)

(instance atpChair2 of PV
	(properties
		y 133
		x 144
		view 450
		cel 1
		priority 9
	)
)

(instance atpChair3 of PV
	(properties
		y 145
		x 150
		view 450
		cel 1
		priority 10
	)
)

(instance atpChair4 of PV
	(properties
		y 155
		x 170
		view 450
		cel 1
		priority 11
	)
)

(instance atpChair5 of PV
	(properties
		y 155
		x 192
		view 450
		cel 1
		priority 11
	)
)

(instance aPatti of Act
	(properties
		y 84
		x 166
		view 452
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors:
			illegalBits: 0
			setPri: 7
			setScript: PattiScript
		)
	)
)

(instance PattiScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(ChangeScriptState self newState 2 2)
		(switch (= state newState)
			(0)
			(1
				(= seconds 0)
				(aPatti view: 452 viewer: pianoCycler)
				(if pianoMusic
					(= temp0 1)
				else
					(= pianoMusic (Random 451 454))
					(= temp0 (Random 1 3))
				)
				(gTheMusic
					stop:
					number: pianoMusic
					loop: temp0
					play: self
				)
			)
			(2
				(gTheMusic number: pianoMusic loop: 1 play:)
				(= pianoMusic 0)
				(= seconds (Random 3 22))
			)
			(3
				(gTheMusic fade:)
				(aPatti viewer: 0 loop: 4 setCel: 0)
				(= seconds 7)
			)
			(4
				(Print 450 61)
				(self changeState: 1)
			)
			(5)
			(6
				(gTheMusic stop: number: 499 loop: global72 play:)
				(= seconds 3)
			)
			(7
				(aPatti setMotion: MoveTo 145 112 self)
			)
			(8
				(Print 450 62)
				(Print 450 63)
				(ego get: 12)
				(PutInRoom 13 340)
				(theGame changeScore: 25)
				(aPatti setLoop: -1 setMotion: MoveTo 168 112 self)
			)
			(9
				(Print 450 64)
				(aPatti setMotion: MoveTo 168 46 self)
			)
			(10
				(Print 450 65 #at -1 144)
				(aPatti dispose:)
				(User canInput: 1)
				(= gCurRoomNum 1004)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(cond 
			((super handleEvent: event))
			((Said 'get,make,(ask<about),gamble/music,buy')
				(gTheMusic fade:)
				(= pianoMusic
					(switch (Random 1 16)
						(1 110)
						(2 120)
						(3 206)
						(4 265)
						(5 323)
						(6 330)
						(7 335)
						(8 395)
						(9 399)
						(0 431)
						(11 435)
						(12 500)
						(13 560)
						(14 599)
						(15 540)
						(else  261)
					)
				)
				(Printf
					450
					56
					(switch pianoMusic
						(110 {Sierra})
						(120 {Title})
						(206 {Binocular})
						(265 {Tawni's})
						(323 {Dewey, Cheatem and Howe})
						(330 {Dale Exotic Dance})
						(335 {Dale's})
						(395 {Bambi's})
						(399 {Fat City})
						(431 {Cherri's Dance})
						(435 {Cherri's})
						(500 {Bamboo})
						(560 {Whitewater Rafting})
						(599 {Nontoonyt Jungle})
						(540 {Feral Pig})
						(else  {Larry Gets Crabs})
					)
				)
				(self changeState: 1)
			)
			((Said 'address[/entertainer]') (if musicLoop (Print 450 57) else (Print 450 58)))
			((Said 'look/entertainer')
				(if (!= gCurRoomNum 1004)
					(Print 450 59)
				else
					(RoomScript changeState: 13)
				)
			)
			((Said '/entertainer') (Print 450 60))
		)
	)
)

(instance pianoCycler of Code
	(properties)
	
	(method (doit)
		(if (not (Random 0 9))
			(aPatti cycleSpeed: (Random 0 1))
		)
		(if (not (Random 0 5)) (aPatti loop: (Random 0 3)))
	)
)

(instance blockPatti of Blk
	(properties
		top 90
		left 157
		bottom 111
		right 177
	)
)
