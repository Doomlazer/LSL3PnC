;;; Sierra Script 1.0 - (do not remove this comment)
(script# 994)
(include sci.sh)
(use Main)
(use Intrface)
(use Sound)
(use Save)
(use Motion)
(use Invent)
(use User)
(use System)


(procedure (localproc_0004 param1 &tmp temp0 [temp1 40] [temp41 40] [temp81 40])
	(= temp0 1)
	(DeviceInfo 0 curSaveDir @temp1)
	(DeviceInfo 1 @temp41)
	(if
		(and
			(DeviceInfo 2 @temp1 @temp41)
			(DeviceInfo 3 @temp41)
		)
		(Format
			@temp81
			994
			6
			(if param1 {SAVE GAME} else {GAME})
			@temp41
		)
		(DeviceInfo 4)
		(= temp0
			(if param1
				(Print
					@temp81
					#font
					0
					#button
					{OK}
					1
					#button
					{Cancel}
					0
					#button
					{Change Directory}
					2
				)
			else
				(Print @temp81 #font 0 #button {OK} 1)
			)
		)
		(if (== temp0 2) (= temp0 (GetDirectory curSaveDir)))
	)
	(return temp0)
)

(class Game of Obj
	(properties
		script 0
	)
	
	(method (play)
		(= theGame self)
		(= curSaveDir (GetSaveDir))
		(if (not (GameIsRestarting)) (GetCWD curSaveDir))
		(self setCursor: waitCursor 1)
		(self init:)
		(self setCursor: normalCursor (HaveMouse))
		(while (not quit)
			(self doit:)
			(= aniInterval (Wait speed))
		)
	)
	
	(method (replay)
		(if lastEvent (lastEvent dispose:))
		(sortedFeatures release:)
		(if modelessDialog (modelessDialog dispose:))
		(cast eachElementDo: #perform RU)
		(theGame setCursor: waitCursor 1)
		(DrawPic
			(curRoom curPic?)
			dpOPEN_NO_TRANSITION
			TRUE
			currentPalette
		)
		(if (!= overlays -1)
			(DrawPic
				overlays
				dpOPEN_NO_TRANSITION
				FALSE
				currentPalette
			)
		)
		(if (curRoom controls?) ((curRoom controls?) draw:))
		(addToPics doit:)
		(theGame setCursor: normalCursor (HaveMouse))
		(SL doit:)
		(DoSound sndDISPOSE)
		(Sound pause: 0)
		(while (not quit)
			(self doit:)
			(= aniInterval (Wait speed))
		)
	)
	
	(method (init &tmp theMotion)
		(= theMotion Motion)
		(= theMotion Sound)
		(= theMotion Save)
		((= cast cast) add:)
		((= features features) add:)
		((= sortedFeatures sFeatures) add:)
		((= sounds sounds) add:)
		((= regions regions) add:)
		((= locales locales) add:)
		((= addToPics addToPics) add:)
		((= timers timers) add:)
		(= curSaveDir (GetSaveDir))
		(Inv init:)
		(User init:)
	)
	
	(method (doit)
		(sounds eachElementDo: #check)
		(timers eachElementDo: #doit)
		(if modelessDialog (modelessDialog check:))
		(Animate (cast elements?) 1)
		(if doMotionCue
			(= doMotionCue 0)
			(cast eachElementDo: #motionCue)
		)
		(if script (script doit:))
		(regions eachElementDo: #doit)
		(if (== newRoomNum curRoomNum) (User doit:))
		(if (!= newRoomNum curRoomNum)
			(self newRoom: newRoomNum)
		)
		(timers eachElementDo: #delete)
		(GameIsRestarting 0)
	)
	
	(method (showSelf)
		(regions showSelf:)
	)
	
	(method (newRoom newRoomNumber &tmp [temp0 4] temp4 temp5)
		(addToPics dispose:)
		(features eachElementDo: #dispose release:)
		(cast eachElementDo: #dispose eachElementDo: #delete)
		(timers eachElementDo: #delete)
		(regions eachElementDo: #perform DNKR release:)
		(locales eachElementDo: #dispose release:)
		(Animate 0)
		(= prevRoomNum curRoomNum)
		(= curRoomNum newRoomNumber)
		(= newRoomNum newRoomNumber)
		(FlushResources newRoomNumber)
		(= temp4 (self setCursor: waitCursor 1))
		(self
			startRoom: curRoomNum
			checkAni:
			setCursor: temp4 (HaveMouse)
		)
		(SetSynonyms regions)
		(while ((= temp5 (Event new: 3)) type?)
			(temp5 dispose:)
		)
		(temp5 dispose:)
	)
	
	(method (checkAni &tmp temp0)
		(Animate (cast elements?) 0)
		(Wait 0)
		(Animate (cast elements?) 0)
		(while (> (Wait 0) aniThreshold)
			(= temp0 (cast firstTrue: #isExtra))
			(if (== temp0 0) (break))
			(temp0 addToPic:)
			(Animate (cast elements?) 0)
			(cast eachElementDo: #delete)
		)
	)
	
	(method (startRoom param1)
		(if debugOn (SetDebug))
		(regions addToFront: (= curRoom (ScriptID param1)))
		(curRoom init:)
		(if demoScripts (curRoom setRegions: 975))
	)
	
	(method (handleEvent event)
		(asm
			lag      useSortedFeatures
			bnt      code_042e
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      128
			eq?     
			bnt      code_042e
			ldi      1
code_042e:
			not     
			bnt      code_044b
			pushi    #handleEvent
			pushi    1
			lsp      event
			lag      regions
			send     6
			bt       code_0447
			pushi    #handleEvent
			pushi    1
			lsp      event
			lag      locales
			send     6
			bnt      code_044b
code_0447:
			ldi      1
			bt       code_045a
code_044b:
			pToa     script
			bnt      code_0458
			pushi    #handleEvent
			pushi    1
			lsp      event
			pToa     script
			send     6
code_0458:
			bnt      code_045c
code_045a:
			ldi      1
code_045c:
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			ret     
		)
	)
	
	(method (changeScore param1)
		(= score (+ score param1))
		(SL doit:)
	)
	
	(method (restart)
		(if modelessDialog (modelessDialog dispose:))
		(RestartGame)
	)
	
	(method (save &tmp [temp0 20] temp20 temp21 temp22)
		(Load rsFONT smallFont)
		(Load rsCURSOR waitCursor)
		(theGame sel_333: gVolume)
		(= volume gVolume)
		(= temp21 (self setCursor: normalCursor))
		(= temp22 (Sound pause: 1))
		(if (localproc_0004 1)
			(if modelessDialog (modelessDialog dispose:))
			(= temp20 (Save doit: @temp0))
			(if (!= temp20 -1)
				(= temp21 (self setCursor: waitCursor 1))
				(if (not (SaveGame name temp20 @temp0 version))
					(Print 994 0 #font 0 #button {OK} 1)
				)
				(self setCursor: temp21 (HaveMouse))
			)
			(localproc_0004 0)
		)
		(Sound pause: temp22)
	)
	
	(method (restore &tmp [temp0 20] temp20 temp21 temp22)
		(Load rsFONT smallFont)
		(Load rsCURSOR waitCursor)
		(= temp21 (self setCursor: normalCursor))
		(= temp22 (Sound pause: 1))
		(if (localproc_0004 1)
			(if modelessDialog (modelessDialog dispose:))
			(= temp20 (Restore doit: &rest))
			(if (!= temp20 -1)
				(self setCursor: waitCursor 1)
				(if (CheckSaveGame name temp20 version)
					(cast eachElementDo: #dispose)
					(cast eachElementDo: #delete)
					(RestoreGame name temp20 version)
				else
					(Print 994 1 #font 0 #button {OK} 1)
					(self setCursor: temp21 (HaveMouse))
				)
			)
			(localproc_0004 0)
		)
		(theGame sel_333: gVolume)
		(DoSound sndPLAY gVolume)
		(Sound sel_334: temp22)
	)
	
	(method (setSpeed newSpeed &tmp theSpeed)
		(= theSpeed speed)
		(= speed newSpeed)
		(return theSpeed)
	)
	
	(method (sel_333 theVolume_2 &tmp theVolume)
		(= theVolume volume)
		(= volume theVolume_2)
		(return theVolume)
	)
	
	(method (setCursor cursorNumber &tmp theTheCursor)
		(= theTheCursor theCursor)
		(= theCursor cursorNumber)
		(SetCursor cursorNumber &rest)
		(return theTheCursor)
	)
	
	(method (showMem)
		(Printf
			{Free Heap: %u Bytes\n Largest ptr: %u Bytes\n FreeHunk: %u KBytes\n Largest hunk: %u Bytes}
			(MemoryInfo 1)
			(MemoryInfo 0)
			(>> (MemoryInfo 3) $0006)
			(MemoryInfo 2)
		)
	)
	
	(method (wordFail param1 &tmp [temp0 100])
		(Printf 994 2 param1)
		(return 0)
	)
	
	(method (syntaxFail)
		(Print 994 3)
	)
	
	(method (semanticFail)
		(Print 994 4)
	)
	
	(method (pragmaFail)
		(Print 994 5)
	)
	
	(method (notify)
	)
	
	(method (setScript theScript)
		(if script (script dispose:))
		(if theScript (theScript init: self &rest))
	)
	
	(method (cue)
		(if script (script cue:))
	)
)

(class Rgn of Obj
	(properties
		script 0
		number 0
		timer 0
		keep 0
		initialized 0
	)
	
	(method (init)
		(if (not initialized)
			(= initialized 1)
			(if (not (regions contains: self))
				(regions addToEnd: self)
			)
			(super init:)
		)
	)
	
	(method (doit)
		(if script (script doit:))
	)
	
	(method (handleEvent event)
		(if script (script handleEvent: event))
		(event claimed?)
	)
	
	(method (dispose)
		(regions delete: self)
		(if (IsObject script) (script dispose:))
		(if (IsObject timer) (timer dispose:))
		(sounds eachElementDo: #clean self)
		(DisposeScript number)
	)
	
	(method (setScript theScript)
		(if (IsObject script) (script dispose:))
		(if theScript (theScript init: self &rest))
	)
	
	(method (cue)
		(if script (script cue:))
	)
	
	(method (newRoom)
	)
	
	(method (notify)
	)
)

(class Rm of Rgn
	(properties
		script 0
		number 0
		timer 0
		keep 0
		initialized 0
		picture 0
		style $ffff
		horizon 0
		controls 0
		north 0
		east 0
		south 0
		west 0
		curPic 0
		picAngle 0
		vanishingX 160
		vanishingY -30000
	)
	
	(method (init &tmp temp0)
		(= number curRoomNum)
		(= controls controls)
		(= perspective picAngle)
		(if picture (self drawPic: picture))
		(switch ((User alterEgo?) edgeHit?)
			(1 ((User alterEgo?) y: 188))
			(4
				((User alterEgo?)
					x: (- 319 ((User alterEgo?) xStep?))
				)
			)
			(3
				((User alterEgo?)
					y: (+ horizon ((User alterEgo?) yStep?))
				)
			)
			(2 ((User alterEgo?) x: 1))
		)
		((User alterEgo?) edgeHit: 0)
	)
	
	(method (doit &tmp temp0)
		(theGame sel_333: gVolume)
		(DoSound sndPLAY gVolume)
		(if script (script doit:))
		(= temp0
			(switch ((User alterEgo?) edgeHit?)
				(1 north)
				(2 east)
				(3 south)
				(4 west)
			)
		)
		(if temp0 (self newRoom: temp0))
	)
	
	(method (dispose)
		(if controls (controls dispose:))
		(super dispose:)
	)
	
	(method (handleEvent event)
		(asm
			pushi    #handleEvent
			pushi    1
			lsp      event
			super    Rgn,  6
			bt       code_08a3
			pToa     controls
			bnt      code_08a1
			pushi    #handleEvent
			pushi    1
			lsp      event
			pToa     controls
			send     6
code_08a1:
			bnt      code_08a5
code_08a3:
			ldi      1
code_08a5:
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			ret     
		)
	)
	
	(method (setRegions scriptNumbers &tmp temp0 theScriptNumbers temp2)
		(= temp0 0)
		(while (< temp0 argc)
			(= theScriptNumbers [scriptNumbers temp0])
			(= temp2 (ScriptID theScriptNumbers))
			(temp2 number: theScriptNumbers)
			(regions add: temp2)
			(if (not (temp2 initialized?)) (temp2 init:))
			(++ temp0)
		)
	)
	
	(method (setLocales scriptNumbers &tmp temp0 theScriptNumbers temp2)
		(= temp0 0)
		(while (< temp0 argc)
			(= theScriptNumbers [scriptNumbers temp0])
			((= temp2 (ScriptID theScriptNumbers))
				number: theScriptNumbers
			)
			(locales add: temp2)
			(temp2 init:)
			(++ temp0)
		)
	)
	
	(method (setFeatures theFeatures &tmp temp0 [temp1 2])
		(= temp0 0)
		(while (< temp0 argc)
			(features add: [theFeatures temp0])
			(++ temp0)
		)
	)
	
	(method (newRoom newRoomNumber)
		(regions
			delete: self
			eachElementDo: #newRoom newRoomNumber
			addToFront: self
		)
		(= newRoomNum newRoomNumber)
		(super newRoom: newRoomNumber)
	)
	
	(method (drawPic picNumber picAnimation)
		(addToPics dispose:)
		(= curPic picNumber)
		(= overlays -1)
		(DrawPic
			picNumber
			(cond 
				((== argc 2) picAnimation)
				((!= style -1) style)
				(else showStyle)
			)
			TRUE
			currentPalette
		)
	)
	
	(method (overlay picNumber picAnimation)
		(= overlays picNumber)
		(DrawPic
			picNumber
			(cond 
				((== argc 2) picAnimation)
				((!= style -1) style)
				(else showStyle)
			)
			FALSE
			currentPalette
		)
	)
)

(class Locale of Obj
	(properties
		number 0
	)
	
	(method (handleEvent event)
		(event claimed?)
	)
	
	(method (dispose)
		(locales delete: self)
		(DisposeScript number)
	)
)

(class SL of Obj
	(properties
		state $0000
		code 0
	)
	
	(method (doit &tmp [temp0 41])
		(if code
			(code doit: @temp0)
			(DrawStatus (if state @temp0 else 0))
		)
	)
	
	(method (enable)
		(= state 1)
		(self doit:)
	)
	
	(method (disable)
		(= state 0)
		(self doit:)
	)
)

(instance cast of EventHandler
	(properties)
)

(instance features of EventHandler
	(properties)
)

(instance sFeatures of EventHandler
	(properties)
	
	(method (delete param1)
		(super delete: param1)
		(if
			(and
				useSortedFeatures
				(param1 isKindOf: Collect)
				(not (OneOf param1 regions locales))
			)
			(param1 release: dispose:)
		)
	)
)

(instance sounds of EventHandler
	(properties)
)

(instance regions of EventHandler
	(properties)
)

(instance locales of EventHandler
	(properties)
)

(instance addToPics of EventHandler
	(properties)
	
	(method (doit)
		(AddToPic elements)
	)
)

(instance controls of Controls
	(properties)
)

(instance timers of Set
	(properties)
)

(instance RU of Code
	(properties)
	
	(method (doit param1 &tmp temp0)
		(if (param1 underBits?)
			(= temp0 (param1 signal?))
			(= temp0 (| temp0 $0001))
			(= temp0 (& temp0 $fffb))
			(param1 underBits: 0 signal: temp0)
		)
	)
)

(instance DNKR of Code
	(properties)
	
	(method (doit param1)
		(if (not (param1 keep?)) (param1 dispose:))
	)
)
