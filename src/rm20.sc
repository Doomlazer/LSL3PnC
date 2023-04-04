;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20)
(include sci.sh)
(use Main)
(use Intrface)
(use File)
(use Game)
(use User)
(use Menu)
(use System)

(public
	rm20 0
)

(instance rm20 of Locale
	(properties)
	
	(method (handleEvent event &tmp temp0 temp1 temp2 temp3 [temp4 30] [temp34 30] [temp64 30])
		(asm
			lag      global64
			not     
			bt       code_001c
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			bnt      code_001d
code_001c:
			ret     
code_001d:
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_0114
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			push    
			ldi      3
			and     
			bnt      code_00a1
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			pushi    7
			pushi    5
			lea      @temp4
			push    
			pushi    20
			pushi    0
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
			callk    Format,  10
			push    
			pushi    67
			pushi    150
			pushi    100
			pushi    33
			pushi    999
			pushi    88
			calle    Print,  14
			sat      temp2
code_0076:
			pushi    2
			pushi    #type
			pushi    0
			pushi    #new
			pushi    0
			class    Event
			send     4
			sat      temp3
			send     4
			ne?     
			bnt      code_0091
			pushi    #dispose
			pushi    0
			lat      temp3
			send     4
			jmp      code_0076
code_0091:
			pushi    #dispose
			pushi    0
			lat      temp2
			send     4
			pushi    #dispose
			pushi    0
			lat      temp3
			send     4
			jmp      code_0111
code_00a1:
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			push    
			ldi      4
			and     
			bnt      code_0111
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			pushi    #canControl
			pushi    1
			pushi    1
			class    User
			send     6
code_00bf:
			pushi    2
			pushi    #type
			pushi    0
			pushi    #new
			pushi    0
			class    Event
			send     4
			sat      temp3
			send     4
			ne?     
			bnt      code_010a
			pushi    1
			lst      temp3
			callk    GlobalToLocal,  2
			pushi    #posn
			pushi    2
			pushi    #x
			pushi    0
			lat      temp3
			send     4
			push    
			pushi    #y
			pushi    0
			lat      temp3
			send     4
			push    
			pushi    210
			pushi    1
			pushi    0
			lag      ego
			send     14
			pushi    2
			pushi    #elements
			pushi    0
			lag      cast
			send     4
			push    
			pushi    0
			callk    Animate,  4
			pushi    #dispose
			pushi    0
			lat      temp3
			send     4
			jmp      code_00bf
code_010a:
			pushi    #dispose
			pushi    0
			lat      temp3
			send     4
code_0111:
			jmp      code_0572
code_0114:
			dup     
			ldi      4
			eq?     
			bnt      code_0494
			pushi    #message
			pushi    0
			lap      event
			send     4
			push    
			dup     
			ldi      11776
			eq?     
			bnt      code_0168
			pushi    1
			pushi    4
			callk    Show,  2
			pushi    2
			pushi    #elements
			pushi    0
			lag      cast
			send     4
			push    
			pushi    0
			callk    Animate,  4
code_013d:
			pushi    0
			pushi    #type
			pushi    0
			pushi    #new
			pushi    0
			class    Event
			send     4
			sap      event
			send     4
			eq?     
			bnt      code_0158
			pushi    #dispose
			pushi    0
			lap      event
			send     4
			jmp      code_013d
code_0158:
			pushi    #dispose
			pushi    0
			lap      event
			send     4
			pushi    1
			pushi    1
			callk    Show,  2
			ret     
			jmp      code_0490
code_0168:
			dup     
			ldi      8192
			eq?     
			bnt      code_0176
			pushi    0
			callk    SetDebug,  0
			jmp      code_0490
code_0176:
			dup     
			ldi      4608
			eq?     
			bnt      code_019e
			pushi    2
			pushi    128
			pushi    1
			lofss    {New Ego View:}
			calle    GetNumber,  2
			sat      temp0
			push    
			callk    Load,  4
			bnt      code_019b
			lat      temp0
			sag      global66
			pushi    0
			callb    NormalEgo,  0
code_019b:
			jmp      code_0490
code_019e:
			dup     
			ldi      8448
			eq?     
			bnt      code_01d0
			lsg      debugOn
			ldi      1
			xor     
			sag      debugOn
			pushi    1
			pushi    4
			lea      @temp4
			push    
			pushi    20
			pushi    1
			lag      debugOn
			bnt      code_01bf
			lofsa    {_}
			jmp      code_01c2
code_01bf:
			lofsa    { NOT_}
code_01c2:
			push    
			callk    Format,  8
			push    
			calle    Print,  2
			jmp      code_0490
code_01d0:
			dup     
			ldi      8704
			eq?     
			bnt      code_01f6
			pushi    2
			pushi    129
			pushi    999
			callk    Load,  4
			bnt      code_01ef
			pushi    3
			pushi    999
			pushi    1
			pushi    1
			callk    DrawPic,  6
			jmp      code_01f3
code_01ef:
			pushi    0
			callk    SetDebug,  0
code_01f3:
			jmp      code_0490
code_01f6:
			dup     
			ldi      5888
			eq?     
			bnt      code_0209
			pushi    #canInput
			pushi    1
			pushi    1
			class    User
			send     6
			jmp      code_0490
code_0209:
			dup     
			ldi      12800
			eq?     
			bnt      code_021b
			pushi    #showMem
			pushi    0
			lag      theGame
			send     4
			jmp      code_0490
code_021b:
			dup     
			ldi      6400
			eq?     
			bnt      code_022a
			pushi    1
			pushi    2
			callk    Show,  2
			jmp      code_0490
code_022a:
			dup     
			ldi      4864
			eq?     
			bnt      code_024a
			pushi    1
			pushi    4
			lea      @temp4
			push    
			pushi    20
			pushi    2
			lsg      curRoomNum
			callk    Format,  8
			push    
			calle    Print,  2
			jmp      code_0490
code_024a:
			dup     
			ldi      7936
			eq?     
			bnt      code_028e
			lag      musicLoop
			bnt      code_026f
			ldi      0
			sag      musicLoop
			ldi      700
			sag      global66
			pushi    3
			lea      @global83
			push    
			pushi    20
			pushi    3
			callk    Format,  6
			sag      global82
			jmp      code_0287
code_026f:
			ldi      1
			sag      musicLoop
			ldi      800
			sag      global66
			pushi    3
			lea      @global83
			push    
			pushi    20
			pushi    4
			callk    Format,  6
			sag      global82
code_0287:
			pushi    0
			callb    NormalEgo,  0
			jmp      code_0490
code_028e:
			dup     
			ldi      5120
			eq?     
			bnt      code_02bf
			pushi    1
			pushi    5
			lea      @temp4
			push    
			pushi    20
			pushi    5
			lsg      global73
			ldi      600
			div     
			push    
			lsg      global73
			ldi      600
			mod     
			push    
			ldi      10
			div     
			push    
			callk    Format,  10
			push    
			calle    Print,  2
			jmp      code_0490
code_02bf:
			dup     
			ldi      12032
			eq?     
			bnt      code_02ce
			pushi    1
			pushi    1
			callk    Show,  2
			jmp      code_0490
code_02ce:
			dup     
			ldi      4352
			eq?     
			bnt      code_0364
			ldi      0
			sat      temp4
			pushi    65535
			pushi    3
			lea      @temp4
			push    
			pushi    50
			lofss    {Writing to "ego.log"}
			calle    GetInput,  6
			ne?     
			bnt      code_0361
			pushi    4
			lea      @temp34
			push    
			pushi    20
			pushi    6
			lsg      curRoomNum
			callk    Format,  8
			pushi    9
			lea      @temp64
			push    
			pushi    20
			pushi    7
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #cel
			pushi    0
			lag      ego
			send     4
			push    
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
			pushi    #priority
			pushi    0
			lag      ego
			send     4
			push    
			callk    Format,  18
			pushi    #name
			pushi    1
			lofss    {ego.log}
			pushi    241
			pushi    5
			lea      @temp34
			push    
			lea      @temp4
			push    
			lofss    {]_}
			lea      @temp64
			push    
			lofss    {\0D\n}
			pushi    243
			pushi    0
			class    gamefile_sh
			send     24
code_0361:
			jmp      code_0490
code_0364:
			dup     
			ldi      11520
			eq?     
			bnt      code_0372
			ldi      1
			sag      quit
			jmp      code_0490
code_0372:
			dup     
			ldi      11264
			eq?     
			bnt      code_0380
			ldi      1
			sag      quit
			jmp      code_0490
code_0380:
			dup     
			ldi      20
			eq?     
			bnt      code_03bf
			pushi    1
			lofss    {Teleport to}
			calle    GetNumber,  2
			sat      temp0
			pushi    2
			pushi    130
			lst      temp0
			callk    Load,  4
			bnt      code_03ad
			pushi    0
			callb    NormalEgo,  0
			pushi    #newRoom
			pushi    1
			lst      temp0
			lag      curRoom
			send     6
			jmp      code_03bc
code_03ad:
			pushi    2
			pushi    20
			pushi    8
			calle    Print,  4
			pushi    0
			callk    SetDebug,  0
code_03bc:
			jmp      code_0490
code_03bf:
			dup     
			ldi      4
			eq?     
			bnt      code_0410
			lag      global97
			bnt      code_03e2
			ldi      0
			sag      global97
			pushi    #draw
			pushi    0
			class    TheMenuBar
			send     4
			pushi    #enable
			pushi    0
			class    SL
			send     4
			pushi    0
			callb    NormalEgo,  0
			jmp      code_040d
code_03e2:
			ldi      1
			sag      global97
			pushi    1
			lofss    {Teleport to}
			calle    GetNumber,  2
			sat      temp0
			pushi    2
			pushi    130
			lst      temp0
			callk    Load,  4
			bnt      code_0409
			pushi    #newRoom
			pushi    1
			lst      temp0
			lag      curRoom
			send     6
			jmp      code_040d
code_0409:
			pushi    0
			callk    SetDebug,  0
code_040d:
			jmp      code_0490
code_0410:
			dup     
			ldi      5
			eq?     
			bnt      code_048a
			pushi    5
			pushi    10
			lea      @temp4
			push    
			lofss    {view %d loop %d cel %d posn %d %d pri %d OnControl $%x Origin on $%x}
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #cel
			pushi    0
			lag      ego
			send     4
			push    
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
			pushi    #priority
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #onControl
			pushi    1
			pushi    1
			lag      ego
			send     6
			push    
			callk    Format,  20
			push    
			pushi    82
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #cel
			pushi    0
			lag      ego
			send     4
			push    
			calle    Print,  10
			jmp      code_0490
code_048a:
			dup     
			ldi      8
			eq?     
			bnt      code_0490
code_0490:
			toss    
			jmp      code_0572
code_0494:
			dup     
			ldi      128
			eq?     
			bnt      code_0572
			pushi    1
			lofss    'tp'
			callk    Said,  2
			bnt      code_04db
			pushi    1
			lofss    {Teleport to}
			calle    GetNumber,  2
			sat      temp0
			pushi    2
			pushi    130
			lst      temp0
			callk    Load,  4
			bnt      code_04cc
			pushi    0
			callb    NormalEgo,  0
			pushi    #newRoom
			pushi    1
			lst      temp0
			lag      curRoom
			send     6
			jmp      code_04db
code_04cc:
			pushi    2
			pushi    20
			pushi    8
			calle    Print,  4
			pushi    0
			callk    SetDebug,  0
code_04db:
			pushi    1
			lofss    'pitch>'
			callk    Said,  2
			bnt      code_0527
			pushi    #saidMe
			pushi    0
			lag      inventory
			send     4
			sat      temp1
			bnt      code_0527
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			pushi    #ownedBy
			pushi    1
			lsg      ego
			lat      temp1
			send     6
			not     
			bnt      code_0512
			pushi    2
			pushi    20
			pushi    9
			calle    Print,  4
			jmp      code_0527
code_0512:
			pushi    2
			pushi    20
			pushi    10
			calle    Print,  4
			pushi    #moveTo
			pushi    1
			pushi    65535
			lat      temp1
			send     6
code_0527:
			pushi    1
			lofss    'get>'
			callk    Said,  2
			bnt      code_0572
			pushi    #saidMe
			pushi    0
			lag      inventory
			send     4
			sat      temp1
			bnt      code_0572
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			pushi    #ownedBy
			pushi    1
			lsg      ego
			lat      temp1
			send     6
			bnt      code_055d
			pushi    2
			pushi    20
			pushi    11
			calle    Print,  4
			jmp      code_0572
code_055d:
			pushi    2
			pushi    20
			pushi    12
			calle    Print,  4
			pushi    #moveTo
			pushi    1
			lsg      ego
			lat      temp1
			send     6
code_0572:
			toss    
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			not     
			bnt      code_0585
			pushi    #handleEvent
			pushi    1
			lsp      event
			super    Locale,  6
code_0585:
			ret     
		)
	)
)
