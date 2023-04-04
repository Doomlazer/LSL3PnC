;;; Sierra Script 1.0 - (do not remove this comment)
(script# 340)
(include sci.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm340 0
)

(local
	[local0 45]
	jokeNum
	theDrinker
	egoSittingLoop
	comedianOnStage
	[wantToSayStr 30]
	[ethnic1Str 15]
	[ethnic2Str 15]
	[ethnic3Str 15]
	local109
	local110
	local111
)
(instance rm340 of Rm
	(properties
		picture 340
		horizon 1
		south 240
	)
	
	(method (init)
		(User canInput: 0 mapKeyToDir: 0)
		(Load rsTEXT 341)
		(Load rsVIEW 705)
		(Load rsVIEW 344)
		(LoadMany 132 21 22 23 24 25 26 27 10 340 341)
		(= gCurRoomNum 16)
		(super init:)
		(addToPics
			add: atpChair
			add: atpManUR
			add: atpManLR
			add: atpManUL_Bottom
			add: atpLadyLR_Bottom
			add: atpLadyUL_Bottom
			doit:
		)
		(aManUL_Top init:)
		(aLadyUL_Top init:)
		(aLadyLR_Top init:)
		(aDrummer init:)
		(aComic init:)
		(aSign init:)
		(if (and (InRoom 13) (ego has: 12))
			(aBottle setPri: 10 ignoreActors: init:)
		)
		(self setScript: RoomScript)
		(NormalEgo 3 (+ 705 currentEgo))
		(ego posn: 159 188 init:)
		(if (Btst 13)
			(aAl loop: 5 cel: 4 init: stopUpd:)
			(aBill loop: 4 cel: 4 init: stopUpd:)
		else
			(aAlTop init:)
			(aBillTop init:)
			(aAl init:)
			(aBill init:)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if comedianOnStage (Print 340 0))
		(= gCurRoomNum 0)
		(super newRoom: newRoomNumber)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(Ok)
				(HandsOff)
				(if (< (ego x?) 167)
					(ego illegalBits: 0 setMotion: MoveTo 153 152 self)
					(= egoSittingLoop 4)
				else
					(ego illegalBits: 0 setMotion: MoveTo 176 152 self)
					(= egoSittingLoop 5)
				)
			)
			(2
				(= cycles (= seconds 0))
				(ego
					view: 705
					loop: egoSittingLoop
					cel: 0
					illegalBits: 0
					setCycle: End self
				)
			)
			(3
				(if (== (ComicScript state?) 0)
					(ComicScript changeState: 1)
				)
				(User canInput: 1)
				(= gCurRoomNum 1004)
			)
			(4
				(ego setCycle: Beg self)
				(theGame setCursor: 999)
			)
			(5
				(= gCurRoomNum 16)
				(NormalEgo 3 (+ 705 currentEgo))
				(if comedianOnStage (Print 340 39))
			)
			(6
				(HandsOff)
				(= wantToSayStr 0)
				(while (> 5 (StrLen @wantToSayStr))
					(GetInput
						@wantToSayStr
						50
						{Just say what you want to say:}
						80
						{Author Interface}
					)
				)
				(aAlTop loop: 2 cel: 0 setCycle: End self)
			)
			(7
				(aAlTop loop: 3 setCycle: Fwd)
				(= seconds 3)
			)
			(8
				(Printf 340 40 @wantToSayStr)
				(aAlTop loop: 2 setCel: 255 setCycle: Beg self)
			)
			(9
				(aBillTop show:)
				(= seconds 3)
			)
			(10
				(Printf 340 41 @wantToSayStr)
				(= seconds 3)
			)
			(11
				(aBillTop hide:)
				(aAlTop setCycle: End self)
			)
			(12
				(aAlTop loop: 3 setCycle: Fwd)
				(= seconds 2)
			)
			(13
				(Printf 340 42 @wantToSayStr)
				(aAl dispose:)
				(aBill dispose:)
				(aAlTop
					posn: 99 191
					loop: 5
					cel: 0
					cycleSpeed: 1
					setCycle: End
				)
				(aBillTop
					posn: 70 190
					loop: 4
					cel: 0
					cycleSpeed: 1
					show:
					setCycle: End self
				)
			)
			(14
				(HandsOn)
				(aAlTop stopUpd:)
				(aBillTop stopUpd:)
				(if (not (Btst 13))
					(Bset 13)
					(theGame changeScore: 5)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			((Said 'boo')
				(if comedianOnStage
					(Print 340 1)
					(Printf 340 2 filthStr)
					(Print 340 3 #at -1 144)
				else
					(Print 340 4)
				)
			)
			((Said 'applaud') (if comedianOnStage (Print 340 5) else (Print 340 4)))
			((and global64 (Said 'test/joke'))
				(= jokeNum
					(GetNumber {First joke (from 1 to LAST JOKE):})
				)
				(Printf 340 6 jokeNum)
			)
			((Said 'address/comedian') (if comedianOnStage (Print 340 7) else (Print 340 8)))
			((Said 'address/bob') (Print 340 9))
			(
				(or
					(Said 'get/microphone')
					(Said 'nightstand,get,jump,climb<on/backstage')
				)
				(Print 340 10)
			)
			((Said 'address/man,bill,babe,al')
				(if
					(or
						(& (ego onControl:) $0008)
						(& (ego onControl:) $0004)
					)
					(if (Btst 13)
						(Print 340 11)
					else
						(self changeState: 6)
					)
				else
					(Printf 340 12 global82)
				)
			)
			((Said 'get/bottle,beer')
				(cond 
					(
					(and (!= gCurRoomNum 16) (!= gCurRoomNum 1004)) (GoodIdea))
					((or (not (InRoom 13)) (not (ego has: 12))) (Print 340 11))
					((not (& (ego onControl:) $0080)) (NotClose))
					(else
						(Ok)
						(aBottle dispose:)
						(theGame changeScore: 15)
						(Print 340 13)
						(ego get: 13)
					)
				)
			)
			(
				(or
					(Said 'nightstand,(get<off),(get<up),(nightstand<up)')
					(Said 'exit/barstool')
				)
				(cond 
					((== gCurRoomNum 16) (YouAre))
					((!= gCurRoomNum 1004) (GoodIdea))
					(else
						(HandsOff)
						(self changeState: 4)
						(theGame setCursor: 999)
					)
				)
			)
			((Said 'lie')
				(cond 
					((not (& (ego onControl:) $0002)) (Print 340 14))
					((== gCurRoomNum 1004) (YouAre))
					((!= gCurRoomNum 16) (GoodIdea))
					(else (self changeState: 1))
				)
			)
			((Said 'look>')
				(cond 
					((Said '/barstool') (Print 340 15))
					((Said '/backstage') (Print 340 16))
					((Said '/mask') (Print 340 17))
					((Said '/awning') (Print 340 18))
					((Said '/cigarette,smoke') (Print 340 19))
					((Said '/burn,burn') (Print 340 20))
					(
						(or
							(Said 'buy/beer,drink,beer')
							(Said 'buy//beer,drink,beer')
							(Said '/attendant,attendant,attendant')
							(Said '//attendant,attendant,attendant')
						)
						(Print 340 21)
					)
					((Said '/door') (Print 340 22))
					((Said '/comedian') (if comedianOnStage (Print 340 23) else (Print 340 24)))
					((Said '/babe') (Print 340 25))
					((Said '/man,couple')
						(if
							(and
								(not (Btst 13))
								(or
									(& (ego onControl:) $0008)
									(& (ego onControl:) $0004)
								)
							)
							(Print 340 26)
						else
							(Print 340 27)
						)
					)
					((Said '/bottle')
						(if (and (InRoom 13) (ego has: 12))
							(Print 340 28)
						else
							(Print 340 11)
						)
					)
					((Said '/al')
						(cond 
							((Btst 13) (Print 340 29) (Print 340 30 #at -1 144))
							((& (ego onControl:) $0008) (Print 340 31))
							(else (Print 340 32))
						)
					)
					((Said '/bill')
						(cond 
							((Btst 13) (event claimed: 0))
							((& (ego onControl:) $0004) (Print 340 33))
							(else (Print 340 34))
						)
					)
					((Said '/buffet')
						(if
							(and
								(& (ego onControl:) $0080)
								(InRoom 13)
								(ego has: 12)
							)
							(Print 340 28)
						else
							(Print 340 35)
						)
					)
					((Said '/bob') (if comedianOnStage (Print 340 36) else (Print 340 37)))
					((Said '[/area,couple]') (Print 340 38))
				)
			)
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(not (& (event modifiers?) emSHIFT))
				)
				(if
					(and
						(== (event type?) evMOUSEBUTTON)
						(== gCurRoomNum 1004)
						(== theCursor 992)
					)
					(HandsOn)
					(self changeState: 4)
				)
				(if (proc0_26 aAl (event x?) (event y?))
					(event claimed: 1)
					(switch theCursor
						(998
							(cond 
								((Btst 13) (Print 340 29) (Print 340 30 #at -1 144))
								((& (ego onControl:) $0008) (Print 340 31))
								(else (Print 340 32))
							)
						)
						(996
							(if
								(or
									(& (ego onControl:) $0008)
									(& (ego onControl:) $0004)
									(& (ego onControl:) $8000)
									(& (ego onControl:) $0001)
								)
								(if (Btst 13)
									(Print 340 11)
								else
									(self changeState: 6)
								)
							else
								(Printf 340 12 global82)
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 95)
						(< (event x?) 123)
						(> (event y?) 146)
						(< (event y?) 175)
						(cast contains: aAl)
					)
					(event claimed: 1)
					(switch theCursor
						(998
							(cond 
								((Btst 13) (Print 340 29) (Print 340 30 #at -1 144))
								((& (ego onControl:) $0008) (Print 340 31))
								(else (Print 340 32))
							)
						)
						(996
							(if
								(or
									(& (ego onControl:) $0008)
									(& (ego onControl:) $0004)
									(& (ego onControl:) $8000)
									(& (ego onControl:) $0001)
								)
								(if (Btst 13)
									(Print 340 11)
								else
									(self changeState: 6)
								)
							else
								(Printf 340 12 global82)
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if (proc0_26 aBill (event x?) (event y?))
					(event claimed: 1)
					(switch theCursor
						(998
							(cond 
								((Btst 13) (event claimed: 0))
								((& (ego onControl:) $0004) (Print 340 33))
								(else (Print 340 34))
							)
						)
						(996
							(if
								(or
									(& (ego onControl:) $0008)
									(& (ego onControl:) $0004)
								)
								(if (Btst 13)
									(Print 340 11)
								else
									(self changeState: 6)
								)
							else
								(Printf 340 12 global82)
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 0)
						(< (event x?) 319)
						(> (event y?) 21)
						(< (event y?) 88)
					)
					(event claimed: 1)
					(switch theCursor
						(998 (Print 340 38))
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 139)
						(< (event x?) 181)
						(> (event y?) 181)
						(< (event y?) 189)
					)
					(event claimed: 1)
					(switch theCursor
						(999
							(ego setMotion: MoveTo 156 190)
						)
						(else  (event claimed: 0))
					)
				)
				(if (proc0_26 aBottle (event x?) (event y?))
					(event claimed: 1)
					(switch theCursor
						(998
							(cond 
								((Btst 13) (event claimed: 0))
								((& (ego onControl:) $0004) (Print 340 33))
								(else (Print 340 34))
							)
						)
						(995
							(cond 
								(
								(and (!= gCurRoomNum 16) (!= gCurRoomNum 1004)) (GoodIdea))
								((or (not (InRoom 13)) (not (ego has: 12))) (Print 340 11))
								((not (& (ego onControl:) $0080)) (NotClose))
								(else
									(Ok)
									(aBottle dispose:)
									(theGame changeScore: 15)
									(Print 340 13)
									(ego get: 13)
								)
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 155)
						(< (event x?) 178)
						(> (event y?) 122)
						(< (event y?) 141)
					)
					(event claimed: 1)
					(switch theCursor
						(995
							(cond 
								((not (& (ego onControl:) $0002)) (Print 340 14))
								((== gCurRoomNum 1004) (YouAre))
								((!= gCurRoomNum 16) (GoodIdea))
								(else (self changeState: 1))
							)
						)
						(else  (event claimed: 0))
					)
				)
			)
		)
	)
)

(instance ComicScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== -1 (gTheMusic prevSignal?)) (== state 7))
			(self cue:)
		)
	)
	
	(method (changeState newState &tmp [temp0 200] temp200 temp201 temp202 temp203 temp204 temp205 temp206 temp207 temp208)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_0f2e
			jmp      code_156d
code_0f2e:
			dup     
			ldi      1
			eq?     
			bnt      code_0f6d
			lsg      global87
			ldi      39
			gt?     
			bnt      code_0f46
			pushi    #setCycle
			pushi    1
			class    Fwd
			push    
			lofsa    aSign
			send     6
code_0f46:
			pushi    #number
			pushi    1
			pushi    10
			pushi    6
			pushi    1
			pushi    65535
			pushi    42
			pushi    0
			lag      gTheMusic
			send     16
			pushi    #loop
			pushi    1
			pushi    2
			pushi    126
			pushi    1
			class    Fwd
			push    
			lofsa    aDrummer
			send     12
			ldi      4
			aTop     seconds
			jmp      code_156d
code_0f6d:
			dup     
			ldi      2
			eq?     
			bnt      code_0fbd
			pushi    2
			pushi    340
			pushi    43
			calle    Print,  4
			lsg      gCurRoomNum
			ldi      1004
			ne?     
			bnt      code_0f93
			pushi    2
			pushi    340
			pushi    44
			calle    Print,  4
code_0f93:
			pushi    #stop
			pushi    0
			pushi    43
			pushi    1
			pushi    340
			pushi    6
			pushi    1
			pushi    1
			pushi    42
			pushi    0
			lag      gTheMusic
			send     20
			pushi    #cycleSpeed
			pushi    1
			pushi    1
			pushi    6
			pushi    1
			pushi    1
			lofsa    aDrummer
			send     12
			ldi      3
			aTop     seconds
			jmp      code_156d
code_0fbd:
			dup     
			ldi      3
			eq?     
			bnt      code_0fd9
			pushi    #setMotion
			pushi    4
			class    MoveTo
			push    
			pushi    160
			pushi    58
			pushSelf
			lofsa    aComic
			send     12
			jmp      code_156d
code_0fd9:
			dup     
			ldi      4
			eq?     
			bnt      code_0ffa
			pushi    #setPri
			pushi    1
			pushi    65535
			pushi    210
			pushi    4
			class    MoveTo
			push    
			pushi    160
			pushi    78
			pushSelf
			lofsa    aComic
			send     18
			jmp      code_156d
code_0ffa:
			dup     
			ldi      5
			eq?     
			bnt      code_1020
			lsg      global87
			ldi      39
			gt?     
			bnt      code_1015
			pushi    #setCel
			pushi    1
			pushi    1
			pushi    197
			pushi    0
			lofsa    aSign
			send     10
code_1015:
			ldi      1
			sal      comedianOnStage
			ldi      3
			aTop     seconds
			jmp      code_156d
code_1020:
			dup     
			ldi      6
			eq?     
			bnt      code_103e
			pushi    #loop
			pushi    1
			pushi    4
			pushi    208
			pushi    1
			lofss    talkCycler
			lofsa    aComic
			send     12
			ldi      3
			aTop     seconds
			jmp      code_156d
code_103e:
			dup     
			ldi      7
			eq?     
			bnt      code_1047
			jmp      code_156d
code_1047:
			dup     
			ldi      8
			eq?     
			bnt      code_1073
			pushi    #cycleSpeed
			pushi    1
			pushi    0
			pushi    6
			pushi    1
			pushi    0
			pushi    201
			pushi    1
			pushi    0
			lofsa    aDrummer
			send     18
			pushi    2
			pushi    340
			pushi    45
			calle    Print,  4
			ldi      3
			aTop     seconds
			jmp      code_156d
code_1073:
			dup     
			ldi      9
			eq?     
			bnt      code_112b
			pushi    #number
			pushi    1
			pushi    341
			pushi    6
			pushi    1
			pushi    65535
			pushi    42
			pushi    0
			lag      gTheMusic
			send     16
			lsg      gCurRoomNum
			ldi      1004
			ne?     
			bnt      code_10a0
			pushi    2
			pushi    340
			pushi    46
			calle    Print,  4
code_10a0:
			pushi    2
			pushi    340
			pushi    47
			calle    Print,  4
			ldi      0
			sal      ethnic1Str
			ldi      0
			sal      ethnic2Str
			ldi      0
			sal      ethnic3Str
code_10b8:
			pushi    3
			pushi    1
			lea      @ethnic1Str
			push    
			callk    StrLen,  2
			gt?     
			bnt      code_10d8
			pushi    3
			lea      @ethnic1Str
			push    
			pushi    15
			lofss    {Ethnic group 1:}
			calle    GetInput,  6
			jmp      code_10b8
code_10d8:
			pushi    3
			pushi    1
			lea      @ethnic2Str
			push    
			callk    StrLen,  2
			gt?     
			bnt      code_10f8
			pushi    3
			lea      @ethnic2Str
			push    
			pushi    15
			lofss    {Ethnic group 2:}
			calle    GetInput,  6
			jmp      code_10d8
code_10f8:
			pushi    3
			pushi    1
			lea      @ethnic3Str
			push    
			callk    StrLen,  2
			gt?     
			bnt      code_1118
			pushi    3
			lea      @ethnic3Str
			push    
			pushi    15
			lofss    {Ethnic group 3:}
			calle    GetInput,  6
			jmp      code_10f8
code_1118:
			pushi    2
			pushi    340
			pushi    48
			calle    Print,  4
			ldi      3
			aTop     seconds
			jmp      code_156d
code_112b:
			dup     
			ldi      10
			eq?     
			bnt      code_1149
			pushi    #loop
			pushi    1
			pushi    4
			pushi    208
			pushi    1
			lofss    talkCycler
			lofsa    aComic
			send     12
			ldi      3
			aTop     seconds
			jmp      code_156d
code_1149:
			dup     
			ldi      11
			eq?     
			bnt      code_1336
			pushi    #setCycle
			pushi    1
			class    Walk
			push    
			pushi    208
			pushi    1
			pushi    0
			lofsa    aComic
			send     12
			ldi      0
			sat      temp204
			+at      temp204
			pushi    2
			pushi    0
			pushi    42
			callk    Random,  4
			sat      temp206
			lal      jokeNum
			bnt      code_117e
			pushi    0
			+al      jokeNum
			sat      temp206
			sali     local0
code_117e:
			lat      temp206
			lsli     local0
			ldi      0
			eq?     
			bnt      code_1324
			pushi    1
			lat      temp206
			sali     local0
			pushi    2
			pushi    49
			pushi    51
			callk    Random,  4
			sal      local109
			sal      local110
			lal      local109
			sal      local111
			lsl      local109
			lal      local110
			eq?     
			bnt      code_11b0
			pushi    2
			pushi    49
			pushi    51
			callk    Random,  4
			sal      local110
code_11b0:
			lsl      local111
			lal      local109
			eq?     
			bt       code_11be
			lsl      local111
			lal      local110
			eq?     
			bnt      code_11ca
code_11be:
			pushi    2
			pushi    49
			pushi    51
			callk    Random,  4
			sal      local111
			jmp      code_11b0
code_11ca:
			ldi      0
			sat      temp207
code_11cf:
			lst      temp207
			ldi      5
			lt?     
			bnt      code_12cb
			ldi      0
			sat      temp208
			pushi    5
			lea      @temp0
			push    
			pushi    340
			pushi    49
			pushi    341
			lst      temp207
			lst      temp206
			ldi      5
			mul     
			add     
			push    
			callk    Format,  10
			pushi    32
			pushi    2
			lea      @temp0
			push    
			pushi    1
			callk    StrAt,  4
			ne?     
			bnt      code_12c5
			ldi      0
			sat      temp205
code_120d:
			lst      temp205
			pushi    1
			lea      @temp0
			push    
			callk    StrLen,  2
			lt?     
			bnt      code_12aa
			pushi    47
			pushi    2
			lea      @temp0
			push    
			lst      temp205
			callk    StrAt,  4
			eq?     
			bnt      code_12a4
			pushi    3
			lea      @temp0
			push    
			lst      temp205
			pushi    37
			callk    StrAt,  6
			pushi    2
			lea      @temp0
			push    
			+st      temp205
			callk    StrAt,  4
			push    
			dup     
			lal      local109
			eq?     
			bnt      code_125b
			lea      @ethnic1Str
			push    
			lat      temp208
			sati     temp200
			+at      temp208
			jmp      code_1292
code_125b:
			dup     
			lal      local110
			eq?     
			bnt      code_1270
			lea      @ethnic2Str
			push    
			lat      temp208
			sati     temp200
			+at      temp208
			jmp      code_1292
code_1270:
			dup     
			lal      local111
			eq?     
			bnt      code_1285
			lea      @ethnic3Str
			push    
			lat      temp208
			sati     temp200
			+at      temp208
			jmp      code_1292
code_1285:
			lea      @ethnic3Str
			push    
			lat      temp208
			sati     temp200
			+at      temp208
code_1292:
			toss    
			pushi    3
			lea      @temp0
			push    
			lst      temp205
			pushi    115
			callk    StrAt,  6
			+at      temp205
code_12a4:
			+at      temp205
			jmp      code_120d
code_12aa:
			pushi    6
			lea      @temp0
			push    
			lst      temp200
			lst      temp201
			lst      temp202
			lst      temp203
			lst      temp204
			calle    Printf,  12
code_12c5:
			+at      temp207
			jmp      code_11cf
code_12cb:
			lsl      jokeNum
			ldi      42
			gt?     
			bnt      code_12f4
			lal      jokeNum
			bnt      code_12e8
			pushi    2
			pushi    340
			pushi    50
			calle    Print,  4
			ldi      0
			sal      jokeNum
			jmp      code_12f4
code_12e8:
			pushi    2
			pushi    340
			pushi    51
			calle    Print,  4
code_12f4:
			pushi    #setCycle
			pushi    1
			class    End
			push    
			lofsa    aDrummer
			send     6
			pushi    1
			pushi    11
			callk    DoSound,  2
			push    
			ldi      5
			gt?     
			bnt      code_1322
			pushi    #number
			pushi    1
			pushi    2
			pushi    21
			pushi    27
			callk    Random,  4
			push    
			pushi    6
			pushi    1
			pushi    1
			pushi    42
			pushi    0
			lag      orchidSeconds
			send     16
code_1322:
			jmp      code_132f
code_1324:
			lst      temp204
			ldi      1000
			ge?     
			ldi      12
			aTop     state
code_132f:
			ldi      2
			aTop     seconds
			jmp      code_156d
code_1336:
			dup     
			ldi      12
			eq?     
			bnt      code_1364
			pushi    #setMotion
			pushi    4
			class    MoveTo
			push    
			pushi    2
			pushi    125
			pushi    195
			callk    Random,  4
			push    
			pushi    2
			pushi    66
			pushi    80
			callk    Random,  4
			push    
			pushSelf
			lofsa    aComic
			send     12
			ldi      9
			aTop     state
			jmp      code_156d
code_1364:
			dup     
			ldi      13
			eq?     
			bnt      code_1394
			pushi    #fade
			pushi    0
			lag      gTheMusic
			send     4
			pushi    2
			pushi    340
			pushi    52
			calle    Print,  4
			pushi    #setMotion
			pushi    4
			class    MoveTo
			push    
			pushi    158
			pushi    75
			pushSelf
			lofsa    aComic
			send     12
			jmp      code_156d
code_1394:
			dup     
			ldi      14
			eq?     
			bnt      code_13c6
			pushi    2
			pushi    340
			pushi    53
			calle    Print,  4
			pushi    1
			pushi    43
			callb    Btst,  2
			not     
			bnt      code_13bf
			pushi    1
			pushi    43
			callb    Bset,  2
			pushi    #changeScore
			pushi    1
			pushi    100
			lag      theGame
			send     6
code_13bf:
			ldi      2
			aTop     seconds
			jmp      code_156d
code_13c6:
			dup     
			ldi      15
			eq?     
			bnt      code_13ee
			pushi    #view
			pushi    1
			pushi    344
			pushi    6
			pushi    1
			pushi    2
			pushi    7
			pushi    1
			pushi    0
			pushi    126
			pushi    2
			class    End
			push    
			pushSelf
			pushi    210
			pushi    1
			pushi    0
			lofsa    aComic
			send     32
			jmp      code_156d
code_13ee:
			dup     
			ldi      16
			eq?     
			bnt      code_140f
			pushi    #setCycle
			pushi    1
			class    Walk
			push    
			pushi    210
			pushi    4
			class    MoveTo
			push    
			pushi    125
			pushi    75
			pushSelf
			lofsa    aComic
			send     18
			jmp      code_156d
code_140f:
			dup     
			ldi      17
			eq?     
			bnt      code_142b
			pushi    #setMotion
			pushi    4
			class    MoveTo
			push    
			pushi    194
			pushi    75
			pushSelf
			lofsa    aComic
			send     12
			jmp      code_156d
code_142b:
			dup     
			ldi      18
			eq?     
			bnt      code_1447
			pushi    #setMotion
			pushi    4
			class    MoveTo
			push    
			pushi    161
			pushi    75
			pushSelf
			lofsa    aComic
			send     12
			jmp      code_156d
code_1447:
			dup     
			ldi      19
			eq?     
			bnt      code_1467
			pushi    #loop
			pushi    1
			pushi    2
			pushi    201
			pushi    1
			pushi    255
			pushi    126
			pushi    2
			class    Beg
			push    
			pushSelf
			lofsa    aComic
			send     20
			jmp      code_156d
code_1467:
			dup     
			ldi      20
			eq?     
			bnt      code_1489
			pushi    #view
			pushi    1
			pushi    343
			pushi    6
			pushi    1
			pushi    2
			pushi    126
			pushi    1
			class    Walk
			push    
			lofsa    aComic
			send     18
			ldi      2
			aTop     seconds
			jmp      code_156d
code_1489:
			dup     
			ldi      21
			eq?     
			bnt      code_14c6
			pushi    2
			pushi    340
			pushi    54
			calle    Print,  4
			pushi    #number
			pushi    1
			pushi    340
			pushi    6
			pushi    1
			pushi    65535
			pushi    42
			pushi    0
			lag      gTheMusic
			send     16
			lsg      global87
			ldi      39
			gt?     
			bnt      code_14bf
			pushi    #setCycle
			pushi    1
			class    Fwd
			push    
			lofsa    aSign
			send     6
code_14bf:
			ldi      2
			aTop     seconds
			jmp      code_156d
code_14c6:
			dup     
			ldi      22
			eq?     
			bnt      code_14e2
			pushi    #setMotion
			pushi    4
			class    MoveTo
			push    
			pushi    160
			pushi    57
			pushSelf
			lofsa    aComic
			send     12
			jmp      code_156d
code_14e2:
			dup     
			ldi      23
			eq?     
			bnt      code_1515
			pushi    2
			pushi    340
			pushi    55
			calle    Print,  4
			pushi    #setMotion
			pushi    4
			class    MoveTo
			push    
			pushi    241
			pushi    58
			pushSelf
			lofsa    aComic
			send     12
			pushi    #fade
			pushi    0
			lag      gTheMusic
			send     4
			ldi      0
			sal      comedianOnStage
			jmp      code_156d
code_1515:
			dup     
			ldi      24
			eq?     
			bnt      code_156d
			pushi    #hide
			pushi    0
			lofsa    aComic
			send     4
			lsg      global87
			ldi      39
			gt?     
			bnt      code_1539
			pushi    #setCel
			pushi    1
			pushi    0
			pushi    197
			pushi    0
			lofsa    aSign
			send     10
code_1539:
			pushi    #stopUpd
			pushi    0
			lofsa    aDrummer
			send     4
			pushi    #number
			pushi    1
			pushi    341
			pushi    6
			pushi    1
			lsg      global72
			pushi    42
			pushi    0
			lag      gTheMusic
			send     16
			pushi    0
			callb    HandsOff,  0
			pushi    #changeState
			pushi    1
			pushi    4
			lofsa    RoomScript
			send     6
			pushi    #setCursor
			pushi    1
			pushi    999
			lag      theGame
			send     6
code_156d:
			toss    
			ret     
		)
	)
)

(instance drinkerScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 10)))
			(1
				(switch (Random 1 3)
					(1 (= theDrinker aLadyLR_Top))
					(2 (= theDrinker aLadyUL_Top))
					(3 (= theDrinker aManUL_Top))
				)
				(theDrinker setCycle: End self)
			)
			(2
				(if (== theDrinker aLadyUL_Top) (= state -1))
				(= cycles (Random 5 22))
			)
			(3
				(theDrinker setCycle: Beg self)
				(= state -1)
			)
		)
	)
)

(instance atpManUR of PV
	(properties
		y 186
		x 247
		view 340
		cel 9
	)
)

(instance atpManLR of PV
	(properties
		y 128
		x 249
		view 340
		cel 8
	)
)

(instance atpChair of PV
	(properties
		y 150
		x 167
		view 340
		priority 11
	)
)

(instance aDrummer of Prop
	(properties
		y 71
		x 79
		view 345
	)
	
	(method (init)
		(super init:)
		(self stopUpd:)
	)
)

(instance aBottle of View
	(properties
		y 119
		x 165
		view 340
		cel 5
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: stopUpd:)
	)
)

(instance aBillTop of Prop
	(properties
		y 155
		x 67
		view 346
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Fwd setPri: 14 ignoreActors: hide:)
	)
)

(instance aBill of View
	(properties
		y 190
		x 70
		view 346
		priority 14
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: stopUpd:)
	)
)

(instance aAlTop of Prop
	(properties
		y 148
		x 102
		view 346
		loop 3
	)
	
	(method (init)
		(super init:)
		(self setPri: 14 ignoreActors: stopUpd:)
	)
)

(instance aAl of View
	(properties
		y 191
		x 99
		view 346
		cel 1
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: stopUpd:)
	)
)

(instance aLadyUL_Top of Prop
	(properties
		y 110
		x 45
		view 340
		loop 2
	)
	
	(method (init)
		(super init:)
		(self setPri: 9 stopUpd:)
	)
)

(instance atpLadyUL_Bottom of PV
	(properties
		y 133
		x 45
		view 340
		cel 2
	)
)

(instance aLadyLR_Top of Prop
	(properties
		y 161
		x 289
		view 340
		loop 4
	)
	
	(method (init)
		(super init:)
		(self setPri: 14 setScript: drinkerScript stopUpd:)
	)
)

(instance atpLadyLR_Bottom of PV
	(properties
		y 183
		x 283
		view 340
		cel 4
	)
)

(instance aManUL_Top of Prop
	(properties
		y 110
		x 281
		view 340
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 9 stopUpd:)
	)
)

(instance atpManUL_Bottom of PV
	(properties
		y 132
		x 281
		view 340
		cel 1
	)
)

(instance talkCycler of Code
	(properties)
	
	(method (doit)
		(if (Random 0 3) (aComic cel: (Random 0 2)))
	)
)

(instance aComic of Act
	(properties
		y 58
		x 241
		view 343
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setScript: ComicScript setCycle: Walk stopUpd:)
	)
)

(instance aSign of Prop
	(properties
		y 50
		x 223
		view 340
		loop 5
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 3 stopUpd:)
	)
)
