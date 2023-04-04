;;; Sierra Script 1.0 - (do not remove this comment)
(script# 140)
(include sci.sh)
(use Main)
(use Intrface)
(use Game)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	rm140 0
)

(local
	local0
	theY
	filthNum
	local3
	local4
	printRet
	local6
	textRes
	local8
	local9
	buffer
	local11
	[local12 96]
	[local108 300]
)
(procedure (localproc_000c param1 param2 &tmp [temp0 200])
	(= theY (+ 42 (* param1 30)))
	(Format @local108 140 7 (+ param1 96))
	(Display
		@local108
		dsCOORD
		150
		theY
		dsCOLOR
		param2
		dsFONT
		4
		dsWIDTH
		10
	)
	(GetFarText textRes (+ (* local4 5) param1) @temp0)
	(Display
		@temp0
		dsCOORD
		165
		theY
		dsCOLOR
		param2
		dsFONT
		4
		dsWIDTH
		135
	)
)

(procedure (localproc_007c param1 param2 param3 param4 param5 &tmp temp0 temp1)
	(= temp0 (param1 x?))
	(= temp1 (param1 y?))
	(return
		(if
			(and
				(> temp0 param2)
				(> temp1 param3)
				(< temp0 param4)
				(< temp1 param5)
			)
			1
		else
			0
		)
	)
)

(procedure (localproc_00b3 param1)
	(= [local9 (/ param1 16)]
		(| [local9 (/ param1 16)] (>> $8000 (mod param1 16)))
	)
)

(procedure (localproc_00cd param1)
	(return
		(if
		(& [local9 (/ param1 16)] (>> $8000 (mod param1 16)))
			1
		else
			0
		)
	)
)

(procedure (localproc_00e7 &tmp temp0 temp1 [temp2 30])
	(if (!= (= temp0 (FOpen {LARRY3.DRV} 1)) -1)
		(= temp1 (FGets @temp2 8 temp0))
		(= local9 (ReadNumber temp1))
		(= buffer (ReadNumber temp1))
		(= local11 (ReadNumber temp1))
	)
	(FClose temp0)
)

(procedure (localproc_0127 &tmp [temp0 40] temp40)
	(if (!= (= temp40 (FOpen {LARRY3.DRV} 2)) -1)
		(Format @temp0 140 13 local9 buffer local11 140 14)
		(FPuts temp40 @temp0)
	)
	(FClose temp40)
)

(procedure (localproc_0163)
	(Graph grFILL_BOX 32 150 189 302 1 15)
	(Graph grUPDATE_BOX 32 150 189 302 1)
)

(instance rm140 of Rm
	(properties
		picture 99
	)
	
	(method (init)
		(Load rsPIC curRoomNum)
		(Load rsSOUND 140)
		(Load rsSOUND 141)
		(Load rsFONT 4)
		(localproc_00e7)
		(while
			(and
				(< (++ local0) 1000)
				(localproc_00cd (- (= textRes (Random 141 161)) 141))
			)
		)
		(if (>= local0 1000)
			(= local9 0)
			(= buffer 0)
			(= local11 0)
			(= textRes (Random 141 161))
		)
		(localproc_00b3 (- textRes 141))
		(localproc_0127)
		(Load rsTEXT textRes)
		(super init:)
		(= global97 0)
		(HandsOn)
		(User canInput: 0)
		(= theCursor 993)
		(ego init: hide:)
		(self setScript: RoomScript)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 temp2 [temp3 200])
		(switch (= state newState)
			(0
				(Print
					140
					1
					#title
					{Hello!___My name is Larry; Larry Laffer!}
					#at
					10
					-1
					#width
					290
				)
				(if
					(==
						0
						(Print
							140
							2
							#title
							{Blush!}
							#button
							{Bail\nOut}
							0
							#button
							{Oh, Go Ahead!\nTry to Offend Me!}
							1
						)
					)
					(= quit 1)
					(return)
				)
				(= temp0
					(Print
						140
						3
						#title
						{Reality Check}
						#button
						{Under 12}
						-1
						#button
						{13 to 17}
						0
						#button
						{18 to 25}
						18
						#button
						{over 25}
						25
					)
				)
				(gTheMusic fade:)
				(switch temp0
					(-1 (Print 140 4) (= quit 1))
					(0
						(Print 140 5)
						(self changeState: 5)
					)
					(else 
						(curRoom drawPic: curRoomNum 7)
						(aSuit init:)
						(addToPics add: atpFace doit:)
						(Format @local108 140 6 temp0)
						(Print @local108 #at -1 144)
						(self cue:)
					)
				)
			)
			(1
				(= theY 42)
				(GetFarText textRes (* local4 5) @local108)
				(= local6 (- (StrAt @local108 0) 48))
				(= temp1 0)
				(while (<= temp1 (StrLen @local108))
					(StrAt @temp3 temp1 (StrAt @local108 (+ temp1 1)))
					(++ temp1)
				)
				(Display
					@temp3
					dsCOORD
					150
					theY
					dsCOLOR
					1
					dsFONT
					4
					dsWIDTH
					150
				)
				(= theY 72)
				(= temp1 1)
				(while (< temp1 5)
					(Format @local108 140 7 (+ temp1 96))
					(Display
						@local108
						dsCOORD
						150
						theY
						dsCOLOR
						1
						dsFONT
						4
						dsWIDTH
						10
					)
					(GetFarText textRes (+ (* local4 5) temp1) @temp3)
					(Display
						@temp3
						dsCOORD
						165
						theY
						dsCOLOR
						1
						dsFONT
						4
						dsWIDTH
						135
					)
					(++ temp1)
					(= theY (+ theY 30))
				)
			)
			(2
				(if (== printRet local6)
					(localproc_000c printRet 2)
					(gTheMusic number: 140 loop: 1 play:)
					(Print 140 8 #at 190 8 #time 3 #dispose)
					(++ filthNum)
					(++ local3)
				else
					(localproc_000c printRet 4)
					(gTheMusic number: 141 loop: 1 play:)
					(Print 140 9 #at 190 8 #time 3 #dispose)
					(if local3 (-- local3))
				)
				(aSuit setCel: local3 forceUpd:)
				(= seconds 3)
			)
			(3
				(cls)
				(localproc_0163)
				(= printRet 0)
				(if (< (++ local4) 5) (= state 0))
				(= cycles 11)
			)
			(4
				(Format
					@local108
					140
					10
					filthNum
					(switch filthNum
						(5 {Totally Raunchiest})
						(4 {Really Filthy})
						(3 {Pretty Dirty})
						(2 {Rather Risque})
						(else  {Mother Goose})
					)
				)
				(localproc_0163)
				(Display
					@local108
					dsCOORD
					160
					60
					dsCOLOR
					1
					dsFONT
					userFont
					dsWIDTH
					130
				)
				(= seconds 9)
			)
			(5
				(if (== filthNum 0) (++ filthNum))
				(= global88 (- filthNum 1))
				(if (!= (= temp2 (FOpen {RESOURCE.LL3} 2)) -1)
					(Format @temp3 140 11 global88)
					(FPuts temp2 @temp3)
				)
				(FClose temp2)
				(curRoom newRoom: 290)
			)
			(10
				(Format
					@local108
					140
					10
					filthNum
					(switch filthNum
						(5 {Totally Raunchiest})
						(4 {Really Filthy})
						(3 {Pretty Dirty})
						(2 {Rather Risque})
						(else  {Mother Goose})
					)
				)
				(localproc_0163)
				(Display
					@local108
					dsCOORD
					160
					60
					dsCOLOR
					1
					dsFONT
					userFont
					dsWIDTH
					130
				)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0 [temp1 33])
		(if (and (not (event claimed?)) (== state 4))
			(self cue:)
		)
		(if
			(or
				(event claimed?)
				(!= state 1)
				(super handleEvent: event)
			)
			(return)
		)
		(switch (event type?)
			(evMOUSEBUTTON
				(cond 
					((localproc_007c event 141 71 300 91) (= printRet 1) (self cue:))
					((localproc_007c event 141 101 300 121) (= printRet 2) (self cue:))
					((localproc_007c event 141 132 300 152) (= printRet 3) (self cue:))
					((localproc_007c event 141 161 300 186) (= printRet 4) (self cue:))
				)
			)
			(evKEYBOARD
				(= temp0 (event modifiers?))
				(switch (event message?)
					(KEY_F2
						(event claimed?)
						(ToggleSound)
					)
					(KEY_A
						(= printRet 1)
						(self cue:)
					)
					(KEY_B
						(= printRet 2)
						(self cue:)
					)
					(KEY_C
						(= printRet 3)
						(self cue:)
					)
					(KEY_D
						(= printRet 4)
						(self cue:)
					)
					(KEY_A
						(= printRet 1)
						(self cue:)
					)
					(KEY_B
						(= printRet 2)
						(self cue:)
					)
					(KEY_C
						(= printRet 3)
						(self cue:)
					)
					(KEY_D
						(= printRet 4)
						(self cue:)
					)
					(KEY_ALT_x
						(if (& temp0 $0004)
							(event claimed: 1)
							(Print 140 0)
							(= filthNum 6)
							(while (u> filthNum 5)
								(= filthNum (GetNumber {Only from 1 to 5!}))
							)
							(self changeState: 4)
						)
					)
				)
			)
		)
	)
)

(instance atpFace of PV
	(properties
		y 52
		x 50
		view 140
		priority 15
	)
)

(instance aSuit of Prop
	(properties
		y 77
		x 83
		view 140
		loop 1
	)
)
