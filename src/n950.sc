;;; Sierra Script 1.0 - (do not remove this comment)
(script# 950)
(include sci.sh)
(use Main)
(use Intrface)
(use rm500)
(use Gauge)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	PnCMenu 0
)

(local
	[local0 50]
	local50 =  4
	local51 =  50
	local52
	local53
	local54
	local55
	[local56 66]
	[local122 42] = [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 -16564 5177 19666 27846 18175 19476 14668 -11668 -14778 -2049 -12039 -6221 -28275 -28200 -29441 -24077 -12441 8987 9137 6655]
	local164 =  500
	local165
	egoEdgeHit
	local167
	[local168 5]
)
(instance PnCMenu of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(self setScript: PnCMenuScript)
		(switch global184
			(0
				(= global186 20)
				(walkIcon setPri: 15 y: global186 init:)
				(lookIcon setPri: 15 y: global186 init:)
				(handIcon setPri: 15 y: global186 init:)
				(talkIcon setPri: 15 y: global186 init:)
				(smellIcon setPri: 15 y: global186 init:)
				(invIcon setPri: 15 y: global186 init:)
				(blockIcon setPri: 15 y: global186 init:)
				(levelsIcon setPri: 15 y: global186 init:)
				(restartIcon setPri: 15 y: global186 init:)
				(saveIcon setPri: 15 y: global186 init:)
				(quitIcon setPri: 15 y: global186 init:)
				(if (!= gTheCursor 900)
					(selectedItem
						setPri: 15
						y: global186
						cel: gTheCursor
						init:
					)
				else
					(selectedItem setPri: 15 y: global186 cel: 0 init:)
				)
			)
			(2
				(= global186 189)
				(walkIcon setPri: 15 y: global186 init:)
				(lookIcon setPri: 15 y: global186 init:)
				(handIcon setPri: 15 y: global186 init:)
				(talkIcon setPri: 15 y: global186 init:)
				(smellIcon setPri: 15 y: global186 init:)
				(invIcon setPri: 15 y: global186 init:)
				(blockIcon setPri: 15 y: global186 init:)
				(levelsIcon setPri: 15 y: global186 init:)
				(restartIcon setPri: 15 y: global186 init:)
				(saveIcon setPri: 15 y: global186 init:)
				(quitIcon setPri: 15 y: global186 init:)
				(if (!= gTheCursor 900)
					(selectedItem
						setPri: 15
						y: global186
						cel: gTheCursor
						init:
					)
				else
					(selectedItem setPri: 15 y: global186 cel: 0 init:)
				)
			)
			(else 
				(walkIcon setPri: 15 y: global186 init:)
				(lookIcon setPri: 15 y: global186 init:)
				(handIcon setPri: 15 y: global186 init:)
				(talkIcon setPri: 15 y: global186 init:)
				(smellIcon setPri: 15 y: global186 init:)
				(invIcon setPri: 15 y: global186 init:)
				(blockIcon setPri: 15 y: global186 init:)
				(levelsIcon setPri: 15 y: global186 init:)
				(restartIcon setPri: 15 y: global186 init:)
				(saveIcon setPri: 15 y: global186 init:)
				(quitIcon setPri: 15 y: global186 init:)
				(if (!= gTheCursor 900)
					(selectedItem
						setPri: 15
						y: global186
						cel: gTheCursor
						init:
						setScript: showButtons
					)
				else
					(selectedItem
						setPri: 15
						y: global186
						cel: 0
						init:
						setScript: showButtons
					)
				)
			)
		)
	)
)

(instance PnCMenuScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (== global184 1)
			(switch global187
				(0
					(cond 
						((or (== global186 0) (== global186 400)) (if (== local52 1) (= global186 0) (= global187 1)))
						((>= global186 20)
							(cond 
								((== local52 0) (= global187 2))
								((>= local54 local51) (= local53 0) (= local52 0) (= local54 0))
								(else (++ local54))
							)
						)
					)
				)
				(1
					(if (>= global186 20) (= global187 0))
				)
			)
		)
		(selectedItem loop: 2 cel: gTheCursor)
		(blockIcon loop: 2 cel: 0)
		(switch theCursor
			(999
				(walkIcon loop: 1)
				(lookIcon loop: 0)
				(handIcon loop: 0)
				(talkIcon loop: 0)
				(smellIcon loop: 0)
			)
			(998
				(walkIcon loop: 0)
				(lookIcon loop: 1)
				(handIcon loop: 0)
				(talkIcon loop: 0)
				(smellIcon loop: 0)
			)
			(995
				(walkIcon loop: 0)
				(lookIcon loop: 0)
				(handIcon loop: 1)
				(talkIcon loop: 0)
				(smellIcon loop: 0)
			)
			(996
				(walkIcon loop: 0)
				(lookIcon loop: 0)
				(handIcon loop: 0)
				(talkIcon loop: 1)
				(smellIcon loop: 0)
			)
			(994
				(walkIcon loop: 0)
				(lookIcon loop: 0)
				(handIcon loop: 0)
				(talkIcon loop: 0)
				(smellIcon loop: 1)
			)
			(gTheCursor
				(walkIcon loop: 0)
				(lookIcon loop: 0 init:)
				(handIcon loop: 0)
				(talkIcon loop: 0)
				(smellIcon loop: 0)
			)
		)
		(if (!= gTheCursor 900)
			(selectedItem view: (+ gTheCursor 900))
		else
			(selectedItem view: 900)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(super handleEvent: event)
		(= temp0 (HaveMouse))
		(if (== (event type?) evMOUSEBUTTON)
			(if modelessDialog (modelessDialog dispose:))
			(blockIcon setCel: gTheCursor)
			(if (& (event modifiers?) emSHIFT)
				(if (== global97 1)
					(event claimed: 1)
				else
					(event claimed: 1)
					(= local54 0)
					(switch theCursor
						(gTheCursor
							(theGame setCursor: 999 temp0)
						)
						(999
							(theGame setCursor: 998 temp0)
						)
						(996
							(theGame setCursor: 994 temp0)
						)
						(998
							(theGame setCursor: 995 temp0)
						)
						(995
							(theGame setCursor: 996 temp0)
						)
						(994
							(if (or (== gTheCursor 900) (== gTheCursor 993))
								(theGame setCursor: 999 temp0)
							else
								(theGame setCursor: gTheCursor temp0)
								(= theCursor gTheCursor)
							)
						)
					)
				)
			)
			(if (not (& (event modifiers?) emSHIFT))
				(cond 
					((== global97 1) (event claimed: 1))
					((< (event y?) 1)
						(event type: 1 claimed: 1)
						(switch global187
							(0
								(if (== global184 1)
									(if (== local52 1) (= local52 0) else (= local52 1))
								)
							)
							(1
								(= global187 2)
								(= local52 0)
							)
							(2
								(= global187 1)
								(= local52 1)
							)
						)
					)
					(
						(and
							(> (event x?) (walkIcon nsLeft?))
							(< (event x?) (walkIcon nsRight?))
							(> (event y?) (walkIcon nsTop?))
							(< (event y?) (walkIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== global187 2) (= global187 1) (= local52 1))
						(= local54 0)
						(theGame setCursor: 999 temp0)
					)
					(
						(and
							(> (event x?) (talkIcon nsLeft?))
							(< (event x?) (talkIcon nsRight?))
							(> (event y?) (talkIcon nsTop?))
							(< (event y?) (talkIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== global187 2) (= global187 1) (= local52 1))
						(= local54 0)
						(theGame setCursor: 996 temp0)
					)
					(
						(and
							(> (event x?) (lookIcon nsLeft?))
							(< (event x?) (lookIcon nsRight?))
							(> (event y?) (lookIcon nsTop?))
							(< (event y?) (lookIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== global187 2) (= global187 1) (= local52 1))
						(= local54 0)
						(theGame setCursor: 998 temp0)
					)
					(
						(and
							(> (event x?) (handIcon nsLeft?))
							(< (event x?) (handIcon nsRight?))
							(> (event y?) (handIcon nsTop?))
							(< (event y?) (handIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== global187 2) (= global187 1) (= local52 1))
						(= local54 0)
						(theGame setCursor: 995 temp0)
					)
					(
						(and
							(> (event x?) (smellIcon nsLeft?))
							(< (event x?) (smellIcon nsRight?))
							(> (event y?) (smellIcon nsTop?))
							(< (event y?) (smellIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== global187 2) (= global187 1) (= local52 1))
						(= local54 0)
						(theGame setCursor: 994 temp0)
					)
					(
						(and
							(> (event x?) (invIcon nsLeft?))
							(< (event x?) (invIcon nsRight?))
							(> (event y?) (invIcon nsTop?))
							(< (event y?) (invIcon nsBottom?))
						)
						(event claimed: 1)
						(if (and (== global184 1) (!= global187 2))
							(= global187 2)
							(= local52 0)
						)
						(= local54 0)
						(invIcon setScript: dotheinv)
					)
					(
						(and
							(> (event x?) (selectedItem nsLeft?))
							(< (event x?) (selectedItem nsRight?))
							(> (event y?) (selectedItem nsTop?))
							(< (event y?) (selectedItem nsBottom?))
						)
						(event claimed: 1)
						(if (== global187 2) (= global187 1) (= local52 1))
						(= local54 0)
						(if (!= gTheCursor 900)
							(theGame setCursor: gTheCursor temp0)
							(= theCursor gTheCursor)
						else
							(Print {You must first selected an item.})
						)
					)
					(
						(and
							(> (event x?) (levelsIcon nsLeft?))
							(< (event x?) (levelsIcon nsRight?))
							(> (event y?) (levelsIcon nsTop?))
							(< (event y?) (levelsIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== global187 2) (= global187 1) (= local52 1))
						(= local54 0)
						(invIcon setScript: dothelevels)
					)
					(
						(and
							(> (event x?) (restartIcon nsLeft?))
							(< (event x?) (restartIcon nsRight?))
							(> (event y?) (restartIcon nsTop?))
							(< (event y?) (restartIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== global187 2) (= global187 1) (= local52 1))
						(= local54 0)
						(restartIcon setScript: dotherestart)
					)
					(
						(and
							(> (event x?) (saveIcon nsLeft?))
							(< (event x?) (saveIcon nsRight?))
							(> (event y?) (saveIcon nsTop?))
							(< (event y?) (saveIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== global187 2) (= global187 1) (= local52 1))
						(= local54 0)
						(saveIcon setScript: dothesave)
					)
					(
						(and
							(> (event x?) (quitIcon nsLeft?))
							(< (event x?) (quitIcon nsRight?))
							(> (event y?) (quitIcon nsTop?))
							(< (event y?) (quitIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== global187 2) (= global187 1) (= local52 1))
						(= local54 0)
						(quitIcon setScript: dothequit)
					)
					(
						(and
							(> (event x?) (ego nsLeft?))
							(< (event x?) (ego nsRight?))
							(> (event y?) (ego nsTop?))
							(< (event y?) (ego nsBottom?))
						)
						(switch theCursor
							(29
								(cond 
									((!= gCurRoomNum 0) (GoodIdea))
									((not (ego has: 13)) (DontHave))
									((== ((Inv at: 13) view?) 28) (Print 500 6 82 28 0 0))
									((>= curRoomNum 500)
										(Ok)
										(theGame changeScore: 20)
										(= expletiveStr 0)
										(gTheMusic number: 500 loop: global72 play:)
										(Print 500 7 82 29 0 0)
										(Print 500 8)
										(PutInRoom 13)
										(NormalEgo)
										(ego baseSetter: SteadyBase setCycle: SlowWalk)
										(= gTheCursor 900)
										(theGame setCursor: 998 (HaveMouse))
										(Bambu changeState: 0)
									)
								)
							)
							(15
								(cond 
									((InRoom 15 484) (Print 520 3))
									((InRoom 15 -1) (DontHave))
									((Btst 72) (Print 520 4))
									((!= gCurRoomNum 0) (GoodIdea))
									(else (BambuRock changeState: 15))
								)
							)
							(11
								(cond 
									((== ((Inv at: 11) view?) 26) (Print 0 #mode))
									((!= gCurRoomNum 0) (GoodIdea))
									(else
										(Ok)
										(theGame setScript: (ScriptID 42))
										(= gTheCursor 900)
										(theGame setCursor: 999)
									)
								)
							)
							(10
								(if (not (ego has: 9))
									(ego get: 9)
									(theGame changeScore: 100)
									(Print 0 42)
								)
							)
							(9
								(Ok)
								(theGame setScript: (ScriptID 45))
							)
							(4
								(cond 
									((not (ego has: 4)) (Print 0 34))
									((== ((Inv at: 4) view?) 23) (Print 0 #window))
									((!= gCurRoomNum 0) (GoodIdea))
									(else
										(Ok)
										(theGame setScript: (ScriptID 44))
										(= gTheCursor 900)
										(theGame setCursor: 999)
									)
								)
							)
							(999 (event type: 1 claimed: 0))
							(997 (event type: 1 claimed: 0))
							(998
								(event type: 1 claimed: 1)
								(if musicLoop
									(switch (Random 0 2)
										(0 (Print 950 36))
										(1 (Print 950 37))
										(2 (Print 950 38))
									)
								else
									(switch (Random 0 2)
										(0 (Print 950 10))
										(1 (Print 950 11))
										(2 (Print 950 12))
									)
								)
							)
							(996
								(event type: 1 claimed: 1)
								(if musicLoop
									(switch (Random 0 2)
										(0 (Print 950 39))
										(1 (Print 950 40))
										(2 (Print 950 #edit))
									)
								else
									(switch (Random 0 2)
										(0 (Print 950 13))
										(1 (Print 950 14))
										(2 (Print 950 15))
									)
								)
							)
							(995
								(event type: 1 claimed: 1)
								(if musicLoop
									(switch (Random 0 2)
										(0 (Print 950 42))
										(1 (Print 950 43))
										(2 (Print 950 44))
									)
								else
									(switch (Random 0 2)
										(0 (Print 950 16))
										(1 (Print 950 17))
										(2 (Print 950 18))
									)
								)
							)
							(else 
								(event type: 1 claimed: 1)
								(if musicLoop
									(switch (Random 0 2)
										(0 (Print 950 45))
										(1 (Print 950 46))
										(2 (Print 950 47))
									)
								else
									(switch (Random 0 2)
										(0 (Print 950 19))
										(1 (Print 950 20))
										(2 (Print 950 21))
									)
								)
							)
						)
					)
					(else
						(switch theCursor
							(999 (event type: 1 claimed: 0))
							(998
								(event type: 1 claimed: 1)
								(switch (Random 42 44)
									(42 (Print 0 146))
									(43 (Print 0 147))
									(44 (Print 0 62))
								)
							)
							(996
								(event type: 1 claimed: 1)
								(Print 0 109 67 -1 144)
							)
							(995
								(event type: 1 claimed: 1)
								(Print 0 158)
							)
							(997 (event type: 1 claimed: 1))
							(else 
								(event type: 1 claimed: 1)
								(Print 0 151)
							)
						)
					)
				)
			)
		)
	)
)

(instance SteadyBase of Code
	(properties)
	
	(method (doit)
		(ego brBottom: (+ (ego y?) 1))
		(ego brTop: (- (ego brBottom?) 2))
		(ego brLeft: (- (ego x?) 10))
		(ego brRight: (+ (ego x?) 10))
	)
)

(instance SlowWalk of Fwd
	(properties)
	
	(method (doit)
		(if
			(or
				(!= (client x?) (client xLast?))
				(!= (client y?) (client yLast?))
			)
			(super doit:)
		)
	)
)

(instance dothequit of Script
	(properties)
	
	(method (changeState newState)
		(= state newState)
		(switch state
			(0
				(quitIcon loop: 1)
				(= cycles 3)
			)
			(1
				(if
				(Print 950 26 80 {Quit} 33 1 81 { Quit_} 1 81 { Oops_} 0)
					(= quit 1)
				else
					(quitIcon loop: 0)
				)
			)
		)
	)
)

(instance dothesave of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(= state newState)
		(switch state
			(0
				(saveIcon loop: 1)
				(= cycles 3)
			)
			(1
				(= temp0
					(Print
						950
						#mode
						81
						{\n__SAVE__\n_}
						1
						81
						{\n__LOAD__\n_}
						2
					)
				)
				(switch temp0
					(0 (= cycles 1))
					(1
						(= global203 (theGame sel_333: gVolume))
						(theGame sel_333: gVolume)
						(Bset 0)
						(= global173 0)
						(= global174 0)
						(= cycles 1)
					)
					(2
						(theGame restore:)
						(theGame sel_333: global203)
						(= cycles 1)
					)
					(else 
						(if (Btst 3)
							(Print 997 8 80 {Not now, I have a headache!})
						else
							(Bset 0)
							(= global173 0)
							(= global174 0)
						)
						(= cycles 1)
					)
				)
			)
			(2 (saveIcon loop: 0))
		)
	)
)

(instance dotherestart of Script
	(properties)
	
	(method (changeState newState)
		(= state newState)
		(switch state
			(0
				(restartIcon loop: 1)
				(= cycles 3)
			)
			(1
				(if
					(Print
						997
						9
						80
						{Restart}
						82
						57
						0
						musicLoop
						33
						bigFont
						81
						{Restart}
						1
						81
						{ Oops_}
						0
					)
					(theGame restart:)
				else
					(restartIcon loop: 0)
					(= newState 0)
				)
			)
		)
	)
)

(instance dothelevels of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 220] theGameHours theOldSysTime)
		(= state newState)
		(switch state
			(0
				(levelsIcon loop: 1)
				(= cycles 3)
			)
			(1
				(= global198
					(proc255_6
						950
						33
						81
						{\nSPEED\n_}
						1
						81
						{\nVOLUME\n_}
						2
						81
						{\nEXPLETIVE\n_}
						3
						81
						{\nAUTOSAVE\n_}
						4
						81
						{\nABOUT\n_}
						5
						81
						{\nTEXT COLOR\n_}
						6
					)
				)
				(switch global198
					(1 (= global193 1) (= state 9))
					(2
						(= global194 2)
						(= state 19)
					)
					(3 (= state 29))
					(4 (= state 39))
					(5 (= state 49))
					(6 (= state 59))
				)
				(= cycles 1)
			)
			(10
				(if (HaveMem 2048)
					(= global195
						((Gauge new:)
							description: (Format @temp0 950 34)
							text: {Animation Speed}
							normal: 10
							higher: {Faster}
							lower: {Slower}
							doit: (- 16 speed)
						)
					)
					(theGame setSpeed: (- 16 global195))
					(DisposeScript 987)
				)
				(= state 99)
				(= cycles 1)
			)
			(20
				(if (HaveMem 2048)
					(= gVolume
						((Gauge new:)
							description: (Format @temp0 950 35)
							text: {Sound Volume}
							normal: 15
							higher: {Louder}
							lower: {Softer}
							doit: (theGame sel_333: gVolume)
						)
					)
					(DoSound sndPLAY gVolume)
					(theGame sel_333: gVolume)
					(DisposeScript 987)
					(= state 99)
					(= cycles 1)
				)
			)
			(30
				(GetInput
					(Format @temp0 filthStr)
					38
					{Enter your favorite expletive:}
				)
				(if (> (StrLen @temp0) 4) (Format filthStr @temp0))
			)
			(40
				(if
					(>
						0
						(= global172
							(GetNumber {Minutes Between Reminders:} global172)
						)
					)
					(= global172 0)
				)
			)
			(50
				(Print
					997
					14
					33
					smallFont
					30
					1
					80
					{An Al Lowe Production}
					82
					51
					0
					0
				)
				(Print
					(Format @temp0 997 15 version)
					#font
					smallFont
					#mode
					1
					#title
					{The Cast of Thousands}
					#at
					-1
					30
					#width
					234
				)
				(Format
					@global121
					997
					2
					(switch global88
						(4 {Totally Raunchiest})
						(3 {Really Filthy})
						(2 {Pretty Dirty})
						(1 {Rather Risque})
						(else  {Mother Goose})
					)
				)
				(Print
					(cond 
						(global69
							(Format
								@temp0
								997
								3
								@global121
								global69
								(if (== global69 1) {} else {s})
								global68
								(if (== global68 1) {} else {s})
								global67
								(if (== global67 1) {} else {s})
								score
								(if (== score 1) {} else {s})
							)
						)
						(score
							(Format
								@temp0
								997
								4
								@global121
								global68
								(if (== global68 1) {} else {s})
								global67
								(if (== global67 1) {} else {s})
								score
								(if (== score 1) {} else {s})
							)
						)
						(else
							(Format
								@temp0
								997
								5
								@global121
								global68
								(if (== global68 1) {} else {s})
								global67
								(if (== global67 1) {} else {s})
							)
						)
					)
					#font
					smallFont
					#mode
					1
					#title
					{Get a Life!}
				)
				(if (and musicLoop (== curRoomNum 500))
					(Print
						{Bambu Map: UP, UP, RIGHT, RIGHT, UP, LEFT, UP, LEFT, UP, UP, UP, LEFT, LEFT, DOWN, LEFT, LEFT, UP, UP, LEFT, UP.}
					)
				)
			)
			(60
				(= theGameHours 16)
				(while
				(and (u> theGameHours 15) (!= theGameHours -1))
					(= theGameHours (GetNumber {New Text Color: (0-15)}))
				)
				(if (!= theGameHours -1)
					(= theOldSysTime 16)
					(while
						(and
							(!= theOldSysTime -1)
							(or
								(u> theOldSysTime 15)
								(== theOldSysTime theGameHours)
							)
						)
						(= theOldSysTime
							(GetNumber {New Background Color: (0-15)})
						)
					)
					(if (!= theOldSysTime -1)
						(= gameHours theGameHours)
						(= oldSysTime theOldSysTime)
					)
				)
				(systemWindow color: gameHours back: oldSysTime)
			)
			(100 (levelsIcon loop: 0))
		)
	)
)

(instance dotheinv of Script
	(properties)
	
	(method (changeState newState)
		(= state newState)
		(switch state
			(0
				(if (== global190 1)
					(= gTheCursor_2 theCursor)
					(invIcon loop: 1)
					(= cycles 3)
				else
					(Print 950 9)
				)
			)
			(1
				(= global191 1)
				(if (HaveMem 1024) (inventory showSelf: ego))
			)
		)
	)
)

(instance walkIcon of Prop
	(properties
		y 189
		x 14
		view 950
	)
)

(instance talkIcon of Prop
	(properties
		y 189
		x 98
		view 950
		cel 3
	)
)

(instance lookIcon of Prop
	(properties
		y 189
		x 42
		view 950
		cel 1
	)
)

(instance handIcon of Prop
	(properties
		y 189
		x 70
		view 950
		cel 2
	)
)

(instance smellIcon of Prop
	(properties
		y 189
		x 126
		view 950
		cel 4
	)
)

(instance invIcon of Prop
	(properties
		y 189
		x 152
		view 950
		cel 5
	)
)

(instance blockIcon of Prop
	(properties
		y 189
		x 186
		view 950
		loop 2
	)
)

(instance levelsIcon of Prop
	(properties
		y 189
		x 220
		view 950
		cel 6
	)
)

(instance restartIcon of Prop
	(properties
		y 189
		x 248
		view 950
		cel 7
	)
)

(instance saveIcon of Prop
	(properties
		y 189
		x 276
		view 950
		cel 8
	)
)

(instance quitIcon of Prop
	(properties
		y 189
		x 304
		view 950
		cel 9
	)
)

(instance selectedItem of Prop
	(properties
		y 189
		x 186
	)
)

(instance showButtons of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(switch global187
			(0)
			(1
				(if (< global186 20)
					(= global186 (+ global186 local50))
					(= local54 0)
				else
					(= global187 0)
					(= local53 1)
				)
			)
			(2
				(if (and (> global186 0) (< global186 400))
					(= global186 (- global186 local50))
				else
					(= global187 0)
					(= global186 400)
				)
			)
		)
		(walkIcon y: global186)
		(lookIcon y: global186)
		(handIcon y: global186)
		(talkIcon y: global186)
		(smellIcon y: global186)
		(invIcon y: global186)
		(blockIcon y: global186)
		(levelsIcon y: global186)
		(restartIcon y: global186)
		(saveIcon y: global186)
		(quitIcon y: global186)
		(if (!= gTheCursor 900)
			(selectedItem y: global186)
		else
			(selectedItem y: global186)
		)
	)
)

(instance BambuRock of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			(
			(and (& (ego onControl: 1) $0200) (== gCurRoomNum 0)) (self changeState: 1))
			(
			(and (== gCurRoomNum 1003) (< 8 (++ local167)))
				(= local167 0)
				(ego
					setLoop: (+ (Random 0 1) (* 2 (< (ego y?) 107)))
				)
			)
			(
			(and (& (ego onControl:) $0002) (== gCurRoomNum 0)) (self changeState: 12))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(if (!= gCurRoomNum 1003)
					(= gCurRoomNum 1003)
					(orchidSeconds stop:)
					(gTheMusic number: 6 loop: -1 play:)
				)
				(HandsOff)
				(ego
					view: 812
					setLoop: 0
					setStep: 1 3
					setCycle: Fwd
					setPri: 8
					illegalBits: 0
				)
				(if (> (ego y?) 137)
					(ego posn: 58 (ego y?) setMotion: MoveTo 58 137 self)
				else
					(ego posn: (- (ego x?) 20) (ego y?))
					(self cue:)
				)
			)
			(2
				(if (> (ego y?) 100)
					(ego setMotion: MoveTo 86 100 self)
				else
					(self cue:)
				)
			)
			(3
				(ego setMotion: MoveTo 105 83 self)
			)
			(4
				(= gCurRoomNum 1001)
				(orchidSeconds stop:)
				(gTheMusic number: 4 loop: 1 play:)
				(ego setPri: 2 setLoop: 2 setCel: 0 setCycle: End self)
			)
			(5
				(ego setStep: 1 8 setMotion: MoveTo (ego x?) 180 self)
			)
			(6
				(cls)
				(if global64
					(NormalEgo)
					(= gCurRoomNum 0)
					(ego posn: 178 100)
				else
					(theGame setScript: (ScriptID 40))
					((ScriptID 40)
						caller: 522
						register: (Format @local56 520 30)
						next: (Format @local122 520 31)
					)
				)
			)
			(7
				(HandsOff)
				(= gCurRoomNum 3)
				(ego
					view: 511
					cycleSpeed: 2
					setLoop: 0
					cel: 0
					setCycle: End self
				)
			)
			(8
				(ego setLoop: 1 cel: 0 setCycle: Fwd)
				(= cycles
					(* 2 (ego cycleSpeed?) 4 (- (NumCels ego) 1))
				)
			)
			(9
				(ego setLoop: 0 setCel: 255 setCycle: Beg self)
			)
			(10 (= seconds 3))
			(11
				(theGame changeScore: 42)
				(Bset 6)
				(NormalEgo 1)
				(= gCurRoomNum 0)
				(Print 520 32)
			)
			(12
				(HandsOff)
				(Print
					(Format @local56 520 33 filthStr)
					#at
					-1
					10
					#dispose
				)
				(= gCurRoomNum 2)
				(orchidSeconds stop:)
				(gTheMusic number: 4 loop: 1 play:)
				(ego
					view: 813
					setLoop: 0
					cel: 0
					illegalBits: 0
					ignoreActors:
					setPri: 2
					setCycle: End self
				)
			)
			(13
				(ego setStep: 1 8 setMotion: MoveTo (ego x?) 188 self)
				(if global64 (= state 5))
			)
			(14 (curRoom newRoom: 525))
			(15
				(HandsOff)
				(Ok)
				(theGame changeScore: 15)
				(Bset 72)
				(ego
					view: 521
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(16
				(ego cel: 3 setCycle: Beg self)
			)
			(17 (NormalEgo))
			(18
				(HandsOff)
				(Ok)
				(theGame changeScore: 40)
				(= gCurRoomNum 13)
				(ego illegalBits: 0 setMotion: MoveTo 213 104 self)
			)
			(19
				(ego
					view: 521
					loop: 1
					cel: 0
					cycleSpeed: 1
					setPri: 10
					setCycle: End self
				)
			)
			(20 (= cycles 11))
			(21
				(ego loop: 2 cel: 0 setCycle: End self)
			)
			(22 (= cycles 11))
			(23
				(Print 520 34 82 15 0 0 67 -1 10)
				(ego setLoop: 3 cel: 0 posn: 212 71 setCycle: End self)
			)
			(24
				(ego setPri: 2)
				(= cycles 5)
			)
			(25
				(ego
					setStep: 1 1
					setMotion: MoveTo (ego x?) (+ 30 (ego y?)) self
				)
			)
			(26
				(Print 520 #window 67 -1 10)
				(curRoom newRoom: 525)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(cond 
			(
				(or
					(Said 'get/drink,water')
					(Said 'drink')
					(Said 'drink/water')
				)
				(cond 
					((Btst 6) (Print 520 0))
					((!= gCurRoomNum 0) (GoodIdea))
					((not (& (ego onControl:) $0200)) (NotClose))
					(else (self changeState: 7))
				)
			)
			((Said 'use,attach/bra')
				(cond 
					((not (ego has: 16)) (DontHave))
					((!= gCurRoomNum 0) (GoodIdea))
					(else (Print 520 1))
				)
			)
			((Said 'use/hose') (if (not (ego has: 15)) (DontHave) else (Print 520 2)))
			((Said 'drain,(off<get),(get<off)/hose')
				(cond 
					((InRoom 15 484) (Print 520 3))
					((InRoom 15 -1) (DontHave))
					((Btst 72) (Print 520 4))
					((!= gCurRoomNum 0) (GoodIdea))
					(else (self changeState: 15))
				)
			)
			((Said '(backdrop<on),wear/hose')
				(cond 
					((InRoom 15 484) (Print 520 3))
					((InRoom 15 -1) (DontHave))
					((!= gCurRoomNum 0) (GoodIdea))
					((not (Btst 72)) (Print 520 5))
					(else (Print 520 6) (theGame changeScore: -15) (Bclr 72))
				)
			)
			((Said 'attach/hose>')
				(cond 
					((not (ego has: 15)) (Print 520 7))
					((not (Btst 72)) (Print 520 8))
					((Said '//!*') (Print 520 9))
					((not (Said '//boulder')) (Print 520 10))
					((!= gCurRoomNum 0) (GoodIdea))
					((not (& (ego onControl:) $0010)) (Print 520 11))
					(else (self changeState: 18))
				)
				(event claimed: 1)
			)
			((Said 'look>')
				(cond 
					((Said '/palm') (Print 520 12))
					((Said '/creek') (Print 520 13))
					((Said '/boulder,boob')
						(if (& (ego onControl:) $0010)
							(Print 520 14)
						else
							(Print 520 15)
						)
					)
					((Said '/ledge,cliff,canyon') (Print 520 16))
					((Said '/cascade,cascade,water') (Print 520 17) (Print 520 18 67 -1 144))
					((Said '[/area]') (Print 520 19))
				)
			)
			((Said '(up<climb),climb/boulder,arch') (Print 520 20))
			(
				(or
					(Said '(climb,go)<(down,above)')
					(Said 'decrease/i')
				)
				(Print 520 21)
			)
			((Said 'climb') (Print 520 22) (Print 520 23 67 -1 144))
			((Said 'drag,grasp,get/bush,hemp') (Print 520 24))
			((Said 'get,use/palm') (Print 520 #time))
			((Said '/bush') (Print 520 26))
			((Said '/arch') (Print 520 27))
			((Said '/flower') (Print 520 28))
			((Said 'jump') (Print 520 29))
		)
	)
)

(instance Bambu of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (ego edgeHit?)
			(= egoEdgeHit (ego edgeHit?))
			(ego edgeHit: 0 illegalBits: 0)
			(theGame setCursor: waitCursor 1)
			(HandsOff)
			(++ expletiveStr)
			(self changeState: 0)
			(cond 
				((< expletiveStr 8) (ego view: 800 moveSpeed: 0))
				((< expletiveStr 14)
					(ego view: 501 moveSpeed: 0)
					(if (!= 501 (gTheMusic number?)) (gTheMusic fade:))
				)
				((< expletiveStr 17)
					(ego view: 502 moveSpeed: 1)
					(if (!= 502 (gTheMusic number?)) (gTheMusic fade:))
				)
				((< expletiveStr 18)
					(ego view: 503 moveSpeed: 2)
					(if (!= 503 (gTheMusic number?)) (gTheMusic fade:))
				)
				(else (ego view: 503 moveSpeed: 3) (self changeState: 2))
			)
			(switch egoEdgeHit
				(1
					(if (== local165 1)
						(gTheMusic fade:)
						(if (not (Btst 37))
							(theGame changeScore: 100)
							(Print 500 0)
							(Print 500 1)
						)
						(curRoom newRoom: 510)
						(return)
					else
						(= local165 (- local165 8))
					)
				)
				(3
					(if (== local165 68)
						(curRoom newRoom: 245)
						(return)
					else
						(= local165 (+ local165 8))
					)
				)
				(2 (++ local165))
				(4 (-- local165))
			)
			(if (== local164 505)
				(= local164 500)
				(switch egoEdgeHit
					(1
						(ego posn: (Random 130 234) 187)
					)
					(3 (ego posn: 177 26))
					(2 (ego posn: 1 74))
					(else  (ego posn: 317 74))
				)
			else
				(= local164 505)
				(switch egoEdgeHit
					(1
						(ego posn: (Random 80 163) 187)
					)
					(3 (ego posn: 188 26))
					(2 (ego posn: 1 83))
					(else  (ego posn: 314 128))
				)
			)
			(proc500_1)
			(Animate (cast elements?) 0)
			(ego illegalBits: -32768)
			(HandsOn)
			(theGame setCursor: normalCursor (HaveMouse))
			(return)
		)
		(if (== (GameIsRestarting) 2)
			(proc500_1)
			(Animate (cast elements?) 0)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1
				(cond 
					(
						(and
							(>= expletiveStr 8)
							(<= expletiveStr 13)
							(!= 501 (gTheMusic number?))
						)
						(gTheMusic number: 501 loop: global72 play:)
					)
					(
						(and
							(>= expletiveStr 14)
							(<= expletiveStr 16)
							(!= 502 (gTheMusic number?))
						)
						(gTheMusic number: 502 loop: global72 play:)
					)
					(
						(and
							(<= expletiveStr 18)
							(>= expletiveStr 17)
							(!= 503 (gTheMusic number?))
						)
						(gTheMusic number: 503 loop: global72 play:)
					)
				)
				(cond 
					((== expletiveStr 4) (Print 500 13))
					((== expletiveStr 8) (Print 500 14))
					((== expletiveStr 12) (Print 500 15))
					((== expletiveStr 16)
						(Print 500 16)
						(Print 500 17)
						(Print 500 18)
						(Print 500 19)
					)
				)
			)
			(2 (= seconds 3))
			(3
				(Print 500 20)
				(= seconds 3)
			)
			(4
				(Print 500 21)
				(= seconds 3)
			)
			(5
				(HandsOff)
				(Print 500 22)
				(ego
					illegalBits: 0
					setMotion: 0
					view: 504
					cel: 0
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(6
				(theGame setScript: (ScriptID 40))
				((ScriptID 40)
					caller: 505
					register: (Format @local56 500 23)
					next: (Format @local122 500 24)
				)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return (event claimed?)))
		(return
			(cond 
				((Said 'get/bamboo') (Print 500 2))
				((Said 'climb/bamboo') (Print 500 3))
				((Said 'attack/bamboo') (Print 500 4))
				((Said 'nightstand,(get,nightstand<up)') (Print 500 5))
				(
					(or
						(Said 'sip/water')
						(Said 'get/drink<--invalid--')
						(Said 'use,drink,drain/water,beer,bottle')
					)
					(cond 
						((!= gCurRoomNum 0) (GoodIdea))
						((not (ego has: 13)) (DontHave))
						((== ((Inv at: 13) view?) 28) (Print 500 6 82 28 0 0))
						(else
							(Ok)
							(theGame changeScore: 20)
							(= expletiveStr 0)
							(gTheMusic number: 500 loop: global72 play:)
							(Print 500 7 82 29 0 0)
							(Print 500 8)
							(PutInRoom 13)
							(NormalEgo)
							(ego baseSetter: SteadyBase setCycle: SlowWalk)
							(self changeState: 0)
						)
					)
				)
				((Said 'look>')
					(cond 
						((Said '[/area]') (Print 500 9) (Print 500 10 67 -1 144))
						((Said '/bamboo')
							(Print 500 11)
							(Print (Format @local56 500 12 global92) #at -1 144)
							(++ global92)
						)
					)
				)
			)
		)
	)
)

(instance aRope of Prop
	(properties
		y 1111
		x 999
		view 530
		loop 1
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: setPri: 7)
	)
)

(instance RopeScript of Script
	(properties)
	
	(method (cue)
		(aRope stopUpd:)
		(Print 530 100 67 10 5 70 290)
	)
)

(instance atpTits of PV
	(properties
		y 157
		x 169
		view 532
		loop 3
		priority 7
		signal $4000
	)
)
