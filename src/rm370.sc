;;; Sierra Script 1.0 - (do not remove this comment)
(script# 370)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm370 0
)

(local
	inputNum1
	inputNum2
	inputNum3
	leaveMsg
	distEgoToLocker
	[str 200]
)
(instance rm370 of Rm
	(properties
		picture 370
		horizon 54
	)
	
	(method (init)
		(Load rsVIEW (+ 706 larryBuffed))
		(Load rsVIEW (+ 702 larryBuffed))
		(Load rsVIEW (+ 704 larryBuffed))
		(Load rsVIEW (+ 700 larryBuffed))
		(if (ego has: 8)
			(Load rsVIEW (+ 703 larryBuffed))
			(Load rsVIEW 8)
		)
		(super init:)
		(if (> machineSpeed 16)
			(aMan1 init:)
			(aMan2 init:)
			(aMan3 init:)
		)
		(aLocker init:)
		(self setScript: RoomScript)
		(NormalEgo)
		(cond 
			((== prevRoomNum 375) (ego loop: 2 posn: 221 58))
			((== prevRoomNum 380) (ego loop: 2 posn: 313 62))
			(else (= currentStatus 7) (ego posn: 307 179))
		)
		(ego
			view:
				(switch currentStatus
					(6 (+ 706 larryBuffed))
					(5 (+ 702 larryBuffed))
					(8 (+ 703 larryBuffed))
					(9 (+ 704 larryBuffed))
					(else  (+ 700 larryBuffed))
				)
			init:
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (< (aLocker y?) 999) (Bset 51))
		(if
			(and
				(== newRoomNumber 375)
				(== currentStatus 8)
				(ego has: 8)
			)
			(Print 370 0 #at 10 -1 #width 290)
			(PutInRoom 8 375)
		)
		(super newRoom: newRoomNumber)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(switch currentStatus
			(5
				(ego observeControl: 4096 8192 ignoreControl: 16384)
			)
			(6
				(ego observeControl: 4096 8192 ignoreControl: 16384)
			)
			(9
				(ego observeControl: 16384 4096 ignoreControl: 8192)
			)
			(8
				(ego observeControl: 8192 4096 ignoreControl: 16384)
			)
			(else 
				(ego observeControl: 8192 16384 ignoreControl: 4096)
			)
		)
		(if (& (ego onControl:) $0800) (ego setPri: 3))
		(if (& (ego onControl:) $0400) (ego setPri: -1))
		(cond 
			((& (ego onControl:) $0004) (curRoom newRoom: 375))
			((& (ego onControl:) $0002)
				(if (not leaveMsg)
					(cond 
						((== currentStatus 7) (= leaveMsg 1) (Print 370 1))
						((== currentStatus 9) (= leaveMsg 1) (Print 370 2))
					)
				)
			)
			((& (ego onControl:) $0010) (curRoom newRoom: 380))
			((& (ego onControl:) $0008)
				(if (not leaveMsg)
					(cond 
						((or (== currentStatus 5) (== currentStatus 6)) (= leaveMsg 1) (Print 370 3))
						((== currentStatus 7)
							(= leaveMsg 1)
							(Print 370 4)
							(if (not larryBuffed) (Print 370 5 #at -1 144))
						)
						((== currentStatus 8) (= leaveMsg 1) (Print 370 6))
					)
				)
			)
			((== 2 (ego edgeHit?))
				(= currentStatus 0)
				(= currentEgoView (+ 700 larryBuffed))
				(curRoom newRoom: 360)
			)
			((& (ego onControl:) $0020)
				(if (not leaveMsg)
					(cond 
						((or (== currentStatus 5) (== currentStatus 6)) (= leaveMsg 1) (Print 370 7))
						((== currentStatus 9) (= leaveMsg 1) (Print 370 8))
						((== currentStatus 8) (= leaveMsg 1) (Print 370 9))
					)
				)
			)
			(else (= leaveMsg 0))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(ego
					illegalBits: 0
					setPri:
					setLoop: 0
					setMotion: MoveTo (ego x?) (+ (ego y?) 20) self
				)
			)
			(2 (= seconds 2))
			(3
				(ego
					setMotion: MoveTo (ego x?) (- (ego y?) 20)
					view:
						(switch currentStatus
							(6 (+ 706 larryBuffed))
							(5 (+ 702 larryBuffed))
							(8 (+ 703 larryBuffed))
							(9 (+ 704 larryBuffed))
							(else  (+ 700 larryBuffed))
						)
				)
				(= cycles 22)
			)
			(4
				(Print @str)
				(NormalEgo 0 (ego view?))
			)
		)
	)
	
	(method (handleEvent event)
		(asm
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      128
			ne?     
			bt       code_04e9
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			bnt      code_04ea
code_04e9:
			ret     
code_04ea:
			pushi    1
			lofss    '/combination'
			callk    Said,  2
			bt       code_0506
			pushi    1
			lofss    'affirmative'
			callk    Said,  2
			bt       code_0506
			pushi    1
			lofss    'unbolt,use,open/locker,door'
			callk    Said,  2
			bnt      code_060e
code_0506:
			pushi    #y
			pushi    0
			lofsa    aLocker
			send     4
			push    
			ldi      999
			lt?     
			bnt      code_051c
			pushi    0
			callb    ItIs,  0
			jmp      code_060b
code_051c:
			pushi    2
			pushi    370
			pushi    10
			calle    Print,  4
code_0528:
			lsl      inputNum1
			ldi      0
			le?     
			bnt      code_053d
			pushi    1
			lofss    {First number:}
			calle    GetNumber,  2
			sal      inputNum1
			jmp      code_0528
code_053d:
			lsl      inputNum2
			ldi      0
			le?     
			bnt      code_0552
			pushi    1
			lofss    {Second number:}
			calle    GetNumber,  2
			sal      inputNum2
			jmp      code_053d
code_0552:
			lsl      inputNum3
			ldi      0
			le?     
			bnt      code_0567
			pushi    1
			lofss    {Third number:}
			calle    GetNumber,  2
			sal      inputNum3
			jmp      code_0552
code_0567:
			pushi    6
			pushi    370
			pushi    11
			lsl      inputNum1
			lsl      inputNum1
			lsl      inputNum2
			lsl      inputNum3
			calle    Printf,  12
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      64
			and     
			not     
			bnt      code_0599
			pushi    2
			pushi    370
			pushi    12
			calle    Print,  4
			jmp      code_05ff
code_0599:
			lsl      inputNum1
			lag      lockerNum1
			ne?     
			bt       code_05b1
			lsl      inputNum2
			lag      lockerNum2
			ne?     
			bt       code_05b1
			lsl      inputNum3
			lag      lockerNum3
			ne?     
			bnt      code_05bf
code_05b1:
			pushi    2
			pushi    370
			pushi    13
			calle    Print,  4
			jmp      code_05ff
code_05bf:
			pushi    1
			pushi    40
			callb    Btst,  2
			not     
			bnt      code_05e6
			pushi    1
			pushi    40
			callb    Bset,  2
			pushi    #changeScore
			pushi    1
			pushi    100
			lag      theGame
			send     6
			pushi    2
			pushi    370
			pushi    14
			calle    Print,  4
			jmp      code_05f2
code_05e6:
			pushi    2
			pushi    370
			pushi    15
			calle    Print,  4
code_05f2:
			pushi    #posn
			pushi    2
			pushi    88
			pushi    65
			lofsa    aLocker
			send     8
code_05ff:
			ldi      0
			sal      inputNum1
			ldi      0
			sal      inputNum2
			ldi      0
			sal      inputNum3
code_060b:
			jmp      code_0ee5
code_060e:
			pushi    1
			lofss    'unknownnumber/'
			callk    Said,  2
			bt       code_0629
			pushi    1
			lofss    '/unknownnumber'
			callk    Said,  2
			bt       code_0629
			pushi    1
			lofss    '//unknownnumber'
			callk    Said,  2
			bnt      code_0638
code_0629:
			pushi    2
			pushi    370
			pushi    16
			calle    Print,  4
			jmp      code_0ee5
code_0638:
			pushi    1
			lofss    'bolt,close/locker,door'
			callk    Said,  2
			bnt      code_0682
			pushi    #y
			pushi    0
			lofsa    aLocker
			send     4
			push    
			ldi      999
			lt?     
			not     
			bnt      code_0657
			pushi    0
			callb    ItIs,  0
			jmp      code_067f
code_0657:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      64
			and     
			not     
			bnt      code_066c
			pushi    0
			callb    NotClose,  0
			jmp      code_067f
code_066c:
			pushi    0
			callb    Ok,  0
			pushi    #posn
			pushi    2
			pushi    1111
			pushi    1111
			lofsa    aLocker
			send     8
code_067f:
			jmp      code_0ee5
code_0682:
			pushi    1
			lofss    'drain,get/art'
			callk    Said,  2
			bnt      code_06ae
			pushi    2
			pushi    370
			pushi    17
			calle    Print,  4
			pushi    5
			pushi    370
			pushi    18
			pushi    67
			pushi    65535
			pushi    144
			calle    Print,  10
			jmp      code_0ee5
code_06ae:
			pushi    1
			lofss    'naked'
			callk    Said,  2
			bt       code_06db
			pushi    1
			lofss    'wear/nothing'
			callk    Said,  2
			bt       code_06db
			pushi    1
			lofss    'get/naked'
			callk    Said,  2
			bt       code_06db
			pushi    1
			lofss    'naked'
			callk    Said,  2
			bt       code_06db
			pushi    1
			lofss    '(alter<(out<of),from),(get<off),drain/cloth,towel,sweatpants,cloth'
			callk    Said,  2
			bnt      code_075a
code_06db:
			lsg      currentStatus
			ldi      5
			eq?     
			bt       code_06e9
			lsg      currentStatus
			ldi      6
			eq?     
			bnt      code_06f7
code_06e9:
			pushi    2
			pushi    370
			pushi    19
			calle    Print,  4
			jmp      code_0757
code_06f7:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      64
			and     
			not     
			bnt      code_0714
			pushi    2
			pushi    370
			pushi    20
			calle    Print,  4
			jmp      code_0757
code_0714:
			pushi    #y
			pushi    0
			lofsa    aLocker
			send     4
			push    
			ldi      999
			lt?     
			not     
			bnt      code_0732
			pushi    2
			pushi    370
			pushi    21
			calle    Print,  4
			jmp      code_0757
code_0732:
			pushi    3
			lea      @str
			push    
			pushi    370
			pushi    22
			callk    Format,  6
			lsg      filthLevel
			ldi      3
			lt?     
			bnt      code_074d
			ldi      6
			sag      currentStatus
			jmp      code_0751
code_074d:
			ldi      5
			sag      currentStatus
code_0751:
			pushi    #changeState
			pushi    1
			pushi    1
			self     6
code_0757:
			jmp      code_0ee5
code_075a:
			pushi    1
			lofss    'dress<get'
			callk    Said,  2
			bt       code_0776
			pushi    1
			lofss    'get/dress'
			callk    Said,  2
			bt       code_0776
			pushi    1
			lofss    'wear,get,(alter<in,to),(backdrop<on)/cloth[<leisure]'
			callk    Said,  2
			bnt      code_080d
code_0776:
			lsg      currentStatus
			ldi      7
			eq?     
			bnt      code_078b
			pushi    2
			pushi    370
			pushi    23
			calle    Print,  4
			jmp      code_080a
code_078b:
			pushi    1
			pushi    50
			callb    Btst,  2
			bnt      code_07a1
			pushi    2
			pushi    370
			pushi    24
			calle    Print,  4
			jmp      code_080a
code_07a1:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      64
			and     
			not     
			bnt      code_07be
			pushi    2
			pushi    370
			pushi    25
			calle    Print,  4
			jmp      code_080a
code_07be:
			pushi    #y
			pushi    0
			lofsa    aLocker
			send     4
			push    
			ldi      999
			lt?     
			not     
			bnt      code_07dc
			pushi    2
			pushi    370
			pushi    21
			calle    Print,  4
			jmp      code_080a
code_07dc:
			pushi    1
			pushi    51
			callb    Btst,  2
			bnt      code_07f2
			pushi    2
			pushi    370
			pushi    26
			calle    Print,  4
			jmp      code_080a
code_07f2:
			pushi    3
			lea      @str
			push    
			pushi    370
			pushi    27
			callk    Format,  6
			ldi      7
			sag      currentStatus
			pushi    #changeState
			pushi    1
			pushi    1
			self     6
code_080a:
			jmp      code_0ee5
code_080d:
			pushi    1
			lofss    'wear,get,(alter<in),(backdrop<on)/towel'
			callk    Said,  2
			bnt      code_08ab
			lsg      currentStatus
			ldi      8
			eq?     
			bnt      code_082c
			pushi    2
			pushi    370
			pushi    28
			calle    Print,  4
			jmp      code_08a8
code_082c:
			pushi    #has
			pushi    1
			pushi    8
			lag      ego
			send     6
			not     
			bnt      code_083f
			pushi    0
			callb    DontHave,  0
			jmp      code_08a8
code_083f:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      64
			and     
			not     
			bnt      code_085c
			pushi    2
			pushi    370
			pushi    29
			calle    Print,  4
			jmp      code_08a8
code_085c:
			pushi    #y
			pushi    0
			lofsa    aLocker
			send     4
			push    
			ldi      999
			lt?     
			not     
			bnt      code_087a
			pushi    2
			pushi    370
			pushi    21
			calle    Print,  4
			jmp      code_08a8
code_087a:
			pushi    1
			pushi    51
			callb    Btst,  2
			bnt      code_0890
			pushi    2
			pushi    370
			pushi    26
			calle    Print,  4
			jmp      code_08a8
code_0890:
			pushi    3
			lea      @str
			push    
			pushi    370
			pushi    30
			callk    Format,  6
			ldi      8
			sag      currentStatus
			pushi    #changeState
			pushi    1
			pushi    1
			self     6
code_08a8:
			jmp      code_0ee5
code_08ab:
			pushi    1
			lofss    'wear,get,(alter<in),(backdrop<on)/sweatpants,(cloth<perspiration)'
			callk    Said,  2
			bnt      code_0967
			lsg      currentStatus
			ldi      9
			eq?     
			bnt      code_08cb
			pushi    2
			pushi    370
			pushi    31
			calle    Print,  4
			jmp      code_0964
code_08cb:
			pushi    1
			pushi    50
			callb    Btst,  2
			bnt      code_08e2
			pushi    2
			pushi    370
			pushi    32
			calle    Print,  4
			jmp      code_0964
code_08e2:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      64
			and     
			not     
			bnt      code_08ff
			pushi    2
			pushi    370
			pushi    25
			calle    Print,  4
			jmp      code_0964
code_08ff:
			pushi    #y
			pushi    0
			lofsa    aLocker
			send     4
			push    
			ldi      999
			lt?     
			not     
			bnt      code_091d
			pushi    2
			pushi    370
			pushi    21
			calle    Print,  4
			jmp      code_0964
code_091d:
			pushi    1
			pushi    51
			callb    Btst,  2
			bnt      code_0933
			pushi    2
			pushi    370
			pushi    26
			calle    Print,  4
			jmp      code_0964
code_0933:
			pushi    3
			lea      @str
			push    
			pushi    370
			pushi    33
			callk    Format,  6
			ldi      9
			sag      currentStatus
			pushi    1
			pushi    41
			callb    Btst,  2
			not     
			bnt      code_095e
			pushi    1
			pushi    41
			callb    Bset,  2
			pushi    #changeScore
			pushi    1
			pushi    4
			lag      theGame
			send     6
code_095e:
			pushi    #changeState
			pushi    1
			pushi    1
			self     6
code_0964:
			jmp      code_0ee5
code_0967:
			pushi    1
			lofss    '(look<for),find/locker,69'
			callk    Said,  2
			bt       code_097a
			pushi    1
			lofss    '(look<for),find//locker,69'
			callk    Said,  2
			bnt      code_0a42
code_097a:
			pushi    4
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			pushi    88
			pushi    65
			callk    GetDistance,  8
			sal      distEgoToLocker
			push    
			ldi      150
			gt?     
			bnt      code_09ab
			pushi    2
			pushi    370
			pushi    34
			calle    Print,  4
			jmp      code_0a3f
code_09ab:
			lsl      distEgoToLocker
			ldi      80
			gt?     
			bnt      code_09c0
			pushi    2
			pushi    370
			pushi    35
			calle    Print,  4
			jmp      code_0a3f
code_09c0:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      512
			and     
			bnt      code_09dd
			pushi    2
			pushi    370
			pushi    36
			calle    Print,  4
			jmp      code_0a3f
code_09dd:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      256
			and     
			bnt      code_09fa
			pushi    2
			pushi    370
			pushi    37
			calle    Print,  4
			jmp      code_0a3f
code_09fa:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      128
			and     
			bnt      code_0a17
			pushi    2
			pushi    370
			pushi    38
			calle    Print,  4
			jmp      code_0a3f
code_0a17:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      64
			and     
			bnt      code_0a33
			pushi    2
			pushi    370
			pushi    39
			calle    Print,  4
			jmp      code_0a3f
code_0a33:
			pushi    2
			pushi    370
			pushi    40
			calle    Print,  4
code_0a3f:
			jmp      code_0ee5
code_0a42:
			pushi    1
			lofss    'pick/bolt,locker,69'
			callk    Said,  2
			bnt      code_0a5a
			pushi    2
			pushi    370
			pushi    41
			calle    Print,  4
			jmp      code_0ee5
code_0a5a:
			pushi    1
			lofss    'caress/locker,top,bay'
			callk    Said,  2
			bnt      code_0a72
			pushi    2
			pushi    370
			pushi    42
			calle    Print,  4
			jmp      code_0ee5
code_0a72:
			pushi    1
			lofss    '(look<in),explore,(look<in)/locker,(door<locker)'
			callk    Said,  2
			bnt      code_0b43
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      64
			and     
			not     
			bnt      code_0a9a
			pushi    2
			pushi    370
			pushi    43
			calle    Print,  4
			jmp      code_0b40
code_0a9a:
			pushi    #y
			pushi    0
			lofsa    aLocker
			send     4
			push    
			ldi      999
			lt?     
			not     
			bnt      code_0ab9
			pushi    2
			pushi    370
			pushi    44
			calle    Print,  4
			jmp      code_0b40
code_0ab9:
			pushi    1
			pushi    51
			callb    Btst,  2
			bnt      code_0ae7
			pushi    2
			pushi    370
			pushi    45
			calle    Print,  4
			pushi    2
			pushi    370
			pushi    46
			calle    Print,  4
			pushi    2
			pushi    370
			pushi    47
			calle    Print,  4
			jmp      code_0b40
code_0ae7:
			lsg      currentStatus
			ldi      7
			eq?     
			bnt      code_0afc
			pushi    2
			pushi    370
			pushi    48
			calle    Print,  4
			jmp      code_0b40
code_0afc:
			lsg      currentStatus
			ldi      8
			eq?     
			bnt      code_0b11
			pushi    2
			pushi    370
			pushi    49
			calle    Print,  4
			jmp      code_0b40
code_0b11:
			lsg      currentStatus
			ldi      9
			eq?     
			bnt      code_0b26
			pushi    2
			pushi    370
			pushi    50
			calle    Print,  4
			jmp      code_0b40
code_0b26:
			lsg      currentStatus
			ldi      5
			eq?     
			bt       code_0b34
			lsg      currentStatus
			ldi      6
			eq?     
			bnt      code_0b40
code_0b34:
			pushi    2
			pushi    370
			pushi    49
			calle    Print,  4
code_0b40:
			jmp      code_0ee5
code_0b43:
			pushi    1
			lofss    'get,spray,wear,use/can,spray,deodorant'
			callk    Said,  2
			bnt      code_0c04
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      64
			and     
			not     
			bnt      code_0b6b
			pushi    2
			pushi    370
			pushi    51
			calle    Print,  4
			jmp      code_0c01
code_0b6b:
			pushi    #y
			pushi    0
			lofsa    aLocker
			send     4
			push    
			ldi      999
			lt?     
			not     
			bnt      code_0b89
			pushi    2
			pushi    370
			pushi    21
			calle    Print,  4
			jmp      code_0c01
code_0b89:
			lsg      currentStatus
			ldi      5
			eq?     
			bt       code_0b9e
			lsg      currentStatus
			ldi      6
			eq?     
			bt       code_0b9e
			lsg      currentStatus
			ldi      8
			eq?     
			bnt      code_0be3
code_0b9e:
			pushi    2
			pushi    370
			pushi    52
			calle    Print,  4
			pushi    2
			pushi    370
			pushi    52
			calle    Print,  4
			pushi    1
			pushi    62
			callb    Bclr,  2
			pushi    1
			pushi    60
			callb    Btst,  2
			not     
			bnt      code_0bd5
			pushi    1
			pushi    60
			callb    Bset,  2
			pushi    #changeScore
			pushi    1
			pushi    27
			lag      theGame
			send     6
code_0bd5:
			pushi    2
			pushi    370
			pushi    53
			calle    Print,  4
			jmp      code_0c01
code_0be3:
			pushi    5
			pushi    370
			pushi    54
			pushi    67
			pushi    65535
			pushi    144
			calle    Print,  10
			pushi    #changeScore
			pushi    1
			pushi    65535
			lag      theGame
			send     6
code_0c01:
			jmp      code_0ee5
code_0c04:
			pushi    1
			lofss    'dry,dry[/body,i]'
			callk    Said,  2
			bt       code_0c29
			pushi    1
			lofss    'caress/self,i'
			callk    Said,  2
			bt       code_0c29
			pushi    1
			lofss    'dry'
			callk    Said,  2
			bt       code_0c29
			pushi    1
			lofss    'use,(dry<with),(dry<off)/towel'
			callk    Said,  2
			bnt      code_0cd9
code_0c29:
			pushi    #has
			pushi    1
			pushi    8
			lag      ego
			send     6
			not     
			bnt      code_0c45
			pushi    2
			pushi    370
			pushi    55
			calle    Print,  4
			jmp      code_0cd6
code_0c45:
			lsg      currentStatus
			ldi      8
			ne?     
			bnt      code_0c68
			lsg      currentStatus
			ldi      6
			ne?     
			bnt      code_0c68
			lsg      currentStatus
			ldi      5
			ne?     
			bnt      code_0c68
			pushi    2
			pushi    370
			pushi    56
			calle    Print,  4
			jmp      code_0cd6
code_0c68:
			pushi    1
			pushi    50
			callb    Btst,  2
			not     
			bnt      code_0c7f
			pushi    2
			pushi    370
			pushi    57
			calle    Print,  4
			jmp      code_0cd6
code_0c7f:
			lsg      currentStatus
			ldi      8
			ne?     
			bnt      code_0ca4
			pushi    #y
			pushi    0
			lofsa    aLocker
			send     4
			push    
			ldi      999
			lt?     
			not     
			bnt      code_0ca4
			pushi    2
			pushi    370
			pushi    58
			calle    Print,  4
			jmp      code_0cd6
code_0ca4:
			pushi    1
			pushi    50
			callb    Bclr,  2
			pushi    1
			pushi    39
			callb    Btst,  2
			not     
			bnt      code_0cc3
			pushi    1
			pushi    39
			callb    Bset,  2
			pushi    #changeScore
			pushi    1
			pushi    22
			lag      theGame
			send     6
code_0cc3:
			pushi    6
			pushi    370
			pushi    59
			pushi    82
			pushi    8
			pushi    0
			pushi    0
			calle    Print,  12
code_0cd6:
			jmp      code_0ee5
code_0cd9:
			pushi    1
			lofss    'address'
			callk    Said,  2
			bnt      code_0cf1
			pushi    2
			pushi    370
			pushi    60
			calle    Print,  4
			jmp      code_0ee5
code_0cf1:
			pushi    1
			lofss    '/combination'
			callk    Said,  2
			bt       code_0d03
			pushi    1
			lofss    'are<where,what/combination,locker'
			callk    Said,  2
			bnt      code_0d12
code_0d03:
			pushi    2
			pushi    370
			pushi    61
			calle    Print,  4
			jmp      code_0ee5
code_0d12:
			pushi    1
			lofss    'look>'
			callk    Said,  2
			bnt      code_0ec7
			pushi    1
			lofss    '/man'
			callk    Said,  2
			bnt      code_0d34
			pushi    2
			pushi    370
			pushi    62
			calle    Print,  4
			jmp      code_0ec5
code_0d34:
			pushi    1
			lofss    '/door,door'
			callk    Said,  2
			bnt      code_0da8
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      2
			and     
			bnt      code_0d59
			pushi    2
			pushi    370
			pushi    63
			calle    Print,  4
			jmp      code_0da5
code_0d59:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      8
			and     
			bnt      code_0d8d
			pushi    2
			pushi    370
			pushi    64
			calle    Print,  4
			pushi    2
			pushi    370
			pushi    65
			calle    Print,  4
			pushi    2
			pushi    370
			pushi    66
			calle    Print,  4
			jmp      code_0da5
code_0d8d:
			pushi    2
			pushi    370
			pushi    67
			calle    Print,  4
			pushi    2
			pushi    370
			pushi    68
			calle    Print,  4
code_0da5:
			jmp      code_0ec5
code_0da8:
			pushi    #y
			pushi    0
			lofsa    aLocker
			send     4
			push    
			ldi      999
			lt?     
			bnt      code_0dcf
			pushi    1
			lofss    '/art'
			callk    Said,  2
			bnt      code_0dcf
			pushi    2
			pushi    370
			pushi    69
			calle    Print,  4
			jmp      code_0ec5
code_0dcf:
			pushi    1
			lofss    '/number'
			callk    Said,  2
			bnt      code_0e30
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      64
			and     
			bt       code_0df5
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      128
			and     
			bnt      code_0e03
code_0df5:
			pushi    2
			pushi    370
			pushi    70
			calle    Print,  4
			jmp      code_0e2d
code_0e03:
			pushi    3
			pushi    370
			pushi    71
			pushi    2
			pushi    1
			pushi    999
			callk    Random,  4
			push    
			calle    Printf,  6
			pushi    5
			pushi    370
			pushi    72
			pushi    67
			pushi    65535
			pushi    144
			calle    Print,  10
code_0e2d:
			jmp      code_0ec5
code_0e30:
			pushi    1
			lofss    '/69,(locker<69)'
			callk    Said,  2
			bnt      code_0e47
			pushi    2
			pushi    370
			pushi    73
			calle    Print,  4
			jmp      code_0ec5
code_0e47:
			pushi    1
			lofss    '/locker<my'
			callk    Said,  2
			bnt      code_0e5e
			pushi    2
			pushi    370
			pushi    61
			calle    Print,  4
			jmp      code_0ec5
code_0e5e:
			pushi    1
			lofss    '/locker,bay'
			callk    Said,  2
			bnt      code_0e75
			pushi    2
			pushi    370
			pushi    74
			calle    Print,  4
			jmp      code_0ec5
code_0e75:
			pushi    1
			lofss    '/sweatpants'
			callk    Said,  2
			bnt      code_0e99
			lsg      currentStatus
			ldi      9
			ne?     
			bnt      code_0e8b
			pushi    0
			callb    DontHave,  0
			jmp      code_0e97
code_0e8b:
			pushi    2
			pushi    370
			pushi    75
			calle    Print,  4
code_0e97:
			jmp      code_0ec5
code_0e99:
			pushi    1
			lofss    '/deodorant,can,spray'
			callk    Said,  2
			bnt      code_0eb0
			pushi    2
			pushi    370
			pushi    76
			calle    Print,  4
			jmp      code_0ec5
code_0eb0:
			pushi    1
			lofss    '[/area]'
			callk    Said,  2
			bnt      code_0ec5
			pushi    2
			pushi    370
			pushi    77
			calle    Print,  4
code_0ec5:
			jmp      code_0ee5
code_0ec7:
			pushi    1
			lofss    '/69'
			callk    Said,  2
			bt       code_0ed9
			pushi    1
			lofss    '//69'
			callk    Said,  2
			bnt      code_0ee5
code_0ed9:
			pushi    2
			pushi    370
			pushi    78
			calle    Print,  4
code_0ee5:
			ret     
		)
	)
)

(instance aLocker of View
	(properties
		y 1111
		x 1111
		view 370
		loop 2
	)
)

(instance aMan1 of Act
	(properties
		view 370
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			ignoreActors:
			illegalBits: 0
			posn: (Random 80 200) 14
			setStep: 1 1
			setCycle: Walk
			setScript: Man1Script
		)
	)
)

(instance Man1Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 6)))
			(1
				(aMan1 setMotion: MoveTo (Random 81 200) 14 self)
			)
			(2 (= seconds (Random 6 12)))
			(3
				(aMan1 setMotion: MoveTo (Random 80 100) 14 self)
				(= state -1)
			)
		)
	)
)

(instance aMan2 of Act
	(properties
		view 370
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			ignoreActors:
			illegalBits: 0
			posn: (Random -60 1) 8
			setStep: 1 1
			setCycle: Walk
			setScript: Man2Script
		)
	)
)

(instance Man2Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 6)))
			(1
				(aMan2 setMotion: MoveTo (Random 2 40) 8 self)
			)
			(2 (= seconds (Random 6 12)))
			(3
				(aMan2 setMotion: MoveTo (Random -60 1) 8 self)
				(= state -1)
			)
		)
	)
)

(instance aMan3 of Act
	(properties
		view 370
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			ignoreActors:
			illegalBits: 0
			posn: (Random -60 0) 20
			setStep: 1 1
			setCycle: Walk
			setScript: Man3Script
		)
	)
)

(instance Man3Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 6)))
			(1
				(aMan3 setMotion: MoveTo (Random 2 22) 20 self)
			)
			(2 (= seconds (Random 6 12)))
			(3
				(aMan3 setMotion: MoveTo (Random -60 1) 20 self)
				(= state -1)
			)
		)
	)
)
