;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include sci.sh)
(use Intrface)
(use LoadMany)
(use Sound)
(use Save)
(use Motion)
(use File)
(use Game)
(use Invent)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	LSL3 0
	NormalEgo 1
	NormalActor 2
	HandsOff 3
	HandsOn 4
	cls 5
	Ok 6
	ItIs 7
	YouAre 8
	GoodIdea 9
	NotClose 10
	AlreadyTook 11
	DontHave 12
	NotifyScript 13
	HaveMem 14
	AddViewToPic 15
	SetOrchidTimer 16
	LogIt 17
	LogPragFail 18
	Bset 19
	Bclr 20
	Btoggle 21
	Btst 22
	InRoom 23
	PutInRoom 24
	PrintDelay 25
	proc0_26 26
	proc0_27 27
	proc0_28 28
)

(local
	ego
	theGame
	curRoom
	speed =  6
	quit
	cast
	regions
	timers
	sounds
	inventory
	addToPics
	curRoomNum
	prevRoomNum
	newRoomNum
	debugOn
	score
	possibleScore
	showStyle =  7
	aniInterval
	theCursor
	normalCursor =  999
	waitCursor =  997
	userFont =  1
	smallFont =  4
	lastEvent
	modelessDialog
	bigFont =  1
	volume =  12
	version =  {x.yyy.zzz}
	locales
	curSaveDir
	
	_2
	_3
	_4
	_5
	_6
	_7
	_8
	_9
	_10
	_11
	_12
	_13
	_14
	_15
	_16
	_17
	_18
	_19
	aniThreshold =  10
	perspective
	features
	sortedFeatures
	useSortedFeatures
	demoScripts
	egoBlindSpot
	overlays =  -1
	doMotionCue
	systemWindow
	demoDialogTime =  3
	currentPalette
	modelessPort
	global63
	global64
	gCurRoomNum
	global66
	global67
	global68
	global69
	global70
	global71
	global72
	global73
	global74
	global75
	global76
	global77
	global78
	global79
	global80
	global81
	global82
	global83
	global84
	global85
	global86
	global87
	global88
	global89
	global90
	global91
	global92
	gScore
	global94
	gTheMusic
	global96
	global97
	global98
	lastSysGlobal
	debugging
	currentStatus
	currentEgoView
	gameSeconds
	gameMinutes
	gameHours
	oldSysTime
	roomSeconds
	musicLoop
	orchidSeconds
	orchidMinutes
	gameFlags
	gGameFlags
	gGameFlags_2
	global114
	global115
	global116
	global117
	currentEgo
	egoName
	gGCurRoomNum
	global121
	global122
	machineSpeed
	filthLevel
	global125
	global126
	global127
	bambooStalksSeen
	oldScore
	dollars
	music
	tawniState
	programControl
	newspaperState
	lawyerState
	vendorView
	saveSpeed
	saveEgoX
	saveEgoY
	showroomState
	myTextColor
	myBackColor
	theDoor
	playingAsPatti
	soundFX
	printTime
	lockerNum1
	lockerNum2
	lockerNum3
	requiredPoundsPumped
	requiredLegCurls
	requiredPullUps
	requiredBarPulls
	larryBuffed
	saveEgoLoop
	oldStatus
	global157
	global158
	global159
	global160
	global161
	global162
	global163
	global164
	global165
	global166
	global167
	global168
	global169
	filthStr
	global171
	global172
	global173
	global174
	global175
	global176
	global177
	global178
	global179
	global180
	global181
	global182
	global183
	global184 =  1
	global185
	global186 =  400
	global187
	gTheCursor_2 =  999
	gTheCursor =  900
	global190 =  1
	global191
	global192
	global193
	global194
	global195
	gVolume =  16
	global197
	global198
	global199
	expletiveStr
	global201
	global202
	global203
)
(procedure (NormalEgo param1 param2)
	(HandsOn)
	(ego edgeHit: 0)
	(switch argc
		(0
			(NormalActor
				ego
				(ego loop?)
				(if (> argc 1) param2 else global66)
			)
		)
		(1
			(NormalActor
				ego
				param1
				(if (> argc 1) param2 else global66)
			)
		)
		(2
			(NormalActor ego param1 param2)
		)
	)
)

(procedure (NormalActor param1 param2 param3)
	(if (> argc 1) (param1 loop: param2))
	(if (> argc 2) (param1 view: param3))
	(param1
		setLoop: -1
		setPri: -1
		setStep: 3 2
		setCycle: Walk
		illegalBits: -32768
		cycleSpeed: 0
		moveSpeed: 0
		ignoreActors: 0
	)
)

(procedure (HandsOff)
	(User canControl: 0 canInput: 0)
	(ego setMotion: 0)
)

(procedure (HandsOn)
	(User canControl: 1 canInput: 1)
	(ego setMotion: 0)
)

(procedure (cls)
	(if modelessDialog (modelessDialog dispose:))
)

(procedure (Ok)
	(Print 0 170)
)

(procedure (ItIs)
	(Print 0 171)
)

(procedure (YouAre)
	(Print 0 172)
)

(procedure (GoodIdea)
	(Print 0 173)
)

(procedure (NotClose)
	(Print 0 174)
)

(procedure (AlreadyTook)
	(Print 0 175)
)

(procedure (DontHave)
	(Print 0 176)
)

(procedure (NotifyScript param1)
	(= param1 (ScriptID param1))
	(param1 notify: &rest)
)

(procedure (HaveMem param1)
	(return
		(if (> (MemoryInfo 1) param1)
			(return 1)
		else
			(Print 0 57)
			(return 0)
		)
	)
)

(procedure (AddViewToPic param1)
	(if param1
		((View new:)
			view: (param1 view?)
			loop: (param1 loop?)
			cel: (param1 cel?)
			setPri: (param1 priority?)
			posn: (param1 x?) (param1 y?)
			addToPic:
		)
		(param1 posn: (param1 x?) (+ 1000 (param1 y?)))
	)
)

(procedure (SetOrchidTimer param1 param2 param3)
	(= global74 param1)
	(= global73 (* 10 (+ param3 (* param2 60))))
)

(procedure (LogIt param1 &tmp [temp0 70])
	(gamefile_sh
		name: {input.log}
		write:
			(Format
				@temp0
				{[r%3d %s v%3d %3dx/%3dy ES%-5d] %s\n}
				curRoomNum
				version
				(ego view?)
				(ego x?)
				(ego y?)
				gCurRoomNum
				param1
			)
		close:
	)
)

(procedure (LogPragFail &tmp [temp0 50])
	(LogIt
		(Format
			@temp0
			{Lame response to "%s"}
			(User inputLineAddr?)
		)
	)
)

(procedure (Bset param1)
	(= [global75 (/ param1 16)]
		(| [global75 (/ param1 16)] (>> $8000 (mod param1 16)))
	)
)

(procedure (Bclr param1)
	(= [global75 (/ param1 16)]
		(&
			[global75 (/ param1 16)]
			(~ (>> $8000 (mod param1 16)))
		)
	)
)

(procedure (Btoggle param1)
	(= [global75 (/ param1 16)]
		(^ [global75 (/ param1 16)] (>> $8000 (mod param1 16)))
	)
)

(procedure (Btst param1)
	(return
		(if
		(& [global75 (/ param1 16)] (>> $8000 (mod param1 16)))
			1
		else
			0
		)
	)
)

(procedure (InRoom param1 param2)
	(return
		(==
			((inventory at: param1) owner?)
			(if (< argc 2) curRoomNum else param2)
		)
	)
)

(procedure (PutInRoom param1 param2)
	((inventory at: param1)
		owner: (if (< argc 2) curRoomNum else param2)
	)
)

(procedure (PrintDelay param1)
	(return (+ 3 (/ (StrLen param1) orchidMinutes)))
)

(procedure ( param1 param2 param3)
	(if
		(and
			(<
				param2
				(+
					(param1 x?)
					(/
						(CelWide (param1 view?) (param1 loop?) (param1 cel?))
						2
					)
				)
			)
			(>
				param2
				(-
					(param1 x?)
					(/
						(CelWide (param1 view?) (param1 loop?) (param1 cel?))
						2
					)
				)
			)
			(< param3 (param1 y?))
			(>
				param3
				(-
					(param1 y?)
					(CelHigh (param1 view?) (param1 loop?) (param1 cel?))
				)
			)
		)
		(return)
	)
)

(procedure ( param1 param2 param3 param4 param5)
	(return
		(if
			(and
				(> (param5 x?) param1)
				(< (param5 x?) param2)
				(> (param5 y?) param3)
				(< (param5 y?) param4)
			)
			(return 1)
		else
			(return 0)
		)
	)
)

(procedure ( param1 param2 param3 param4 param5 &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6)
	(if (< argc 3)
		(= temp0 (param1 view?))
		(= temp1 (param1 loop?))
		(= temp2 (param1 cel?))
		(if (> argc 1) (= temp3 param2) else (= temp3 4))
		(Graph
			grDRAW_LINE
			(param1 y?)
			(+ (param1 x?) (/ (CelWide temp0 temp1 temp2) 2))
			(param1 y?)
			(- (param1 x?) (/ (CelWide temp0 temp1 temp2) 2))
			temp3
		)
		(Graph
			grDRAW_LINE
			(param1 y?)
			(+ (param1 x?) (/ (CelWide temp0 temp1 temp2) 2))
			(- (param1 y?) (CelHigh temp0 temp1 temp2))
			(+ (param1 x?) (/ (CelWide temp0 temp1 temp2) 2))
			temp3
		)
		(Graph
			grDRAW_LINE
			(- (param1 y?) (CelHigh temp0 temp1 temp2))
			(+ (param1 x?) (/ (CelWide temp0 temp1 temp2) 2))
			(- (param1 y?) (CelHigh temp0 temp1 temp2))
			(- (param1 x?) (/ (CelWide temp0 temp1 temp2) 2))
			temp3
		)
		(Graph
			grDRAW_LINE
			(- (param1 y?) (CelHigh temp0 temp1 temp2))
			(- (param1 x?) (/ (CelWide temp0 temp1 temp2) 2))
			(param1 y?)
			(- (param1 x?) (/ (CelWide temp0 temp1 temp2) 2))
			temp3
		)
	else
		(= temp1 param1)
		(= temp4 param2)
		(= temp5 param3)
		(= temp6 param4)
		(if (> argc 4) (= temp3 param5) else (= temp3 4))
		(Graph grDRAW_LINE temp5 temp1 temp5 temp4 temp3)
		(Graph grDRAW_LINE temp5 temp4 temp6 temp4 temp3)
		(Graph grDRAW_LINE temp6 temp4 temp6 temp1 temp3)
		(Graph grDRAW_LINE temp6 temp1 temp5 temp1 temp3)
	)
)

(class Iitem of InvI
	(properties
		said 0
		description 0
		owner 0
		view 0
		loop 0
		cel 0
		script 0
	)
	
	(method (showSelf)
		(Print 30 view 80 name 82 view 0 0)
	)
)

(instance LSL3 of Game
	(properties)
	
	(method (init &tmp temp0)
		((= systemWindow theWindow)
			color: (= gameHours 1)
			back: (= oldSysTime 15)
		)
		(super init:)
		(Bset 14)
		(= global171 {"My name is Larry; Larry Laffer."})
		(= version {1.021})
		(= volume gVolume)
		(DoSound sndPLAY volume)
		(SL code: statusCode)
		(TheMenuBar init:)
		(scoreSound owner: self init:)
		((= gTheMusic theMusic) owner: self init:)
		((= orchidSeconds theSoundFX) owner: self init:)
		(User alterEgo: (= ego ego) blocks: 0 y: 150)
		(if (HaveMouse)
			(theGame setCursor: normalCursor 1)
		else
			(theGame setCursor: normalCursor 1 304 174)
		)
		(Load rsFONT (= bigFont 0))
		(Load rsFONT (= userFont 1))
		(Load rsFONT (= smallFont 4))
		(Load rsFONT 999)
		(Load rsCURSOR normalCursor)
		(Load rsCURSOR waitCursor)
		(Load rsCURSOR 666)
		(Load rsCURSOR 992)
		(Inv
			add:
				Nothing
				Credit_Card_______
				Ginsu_Knife_____
				Granadilla_Wood____
				Native_Grass_
				Soap
				A_Twenty_Dollar_Bill_____
				Land_Deed
				Beach_Towel____
				Spa_Keycard___
				Divorce_Decree
				some_Orchids__
				Penthouse_Key__
				Bottle_of_Wine_
				Panties
				Hose
				Bra
				Dress
				Magic_Marker____
				Coconut
				Marijuana
		)
		(if (GameIsRestarting)
			(= temp0 290)
		else
			(= temp0 120)
		)
		(self newRoom: temp0)
	)
	
	(method (doit &tmp [temp0 50])
		(super doit:)
		(if (Btst 0) (Bclr 0) (theGame save:))
		(if global97 (User canControl: 0 canInput: 0))
		(if (!= global70 (= global70 (GetTime 1)))
			(if (>= (++ global67) 60)
				(= global67 0)
				(if (>= (++ global68) 60)
					(= global68 0)
					(++ global69)
				)
			)
			(++ global71)
			(if
				(and
					(< score 20)
					(> global68 19)
					(> global71 8)
					(< global71 69)
					(User canControl:)
					(== global67 1)
				)
				(++ global67)
				(Print 0 0)
				(Print 0 1 #at -1 144)
			)
			(if
			(and (not (Btst 4)) global172 (>= (++ global174) 60))
				(= global174 0)
				(if (>= (++ global173) global172)
					(= global173 0)
					(if
						(Print
							(Format
								@temp0
								0
								2
								global172
								(if (== global172 1) {} else {s})
							)
							#title
							{AutoSave\05 Warning!}
							#icon
							52
							0
							0
							#font
							bigFont
							#button
							{Save Now!}
							1
							#button
							{Screw it.}
							0
							#at
							-1
							10
						)
						(theGame save:)
					)
				)
			)
		)
		(if (> gScore score)
			(if (> global87 39) (-- gScore) else (= gScore score))
			(SL doit:)
		)
		(if (< gScore score)
			(if (> global87 39) (++ gScore) else (= gScore score))
			(SL doit:)
		)
		(cond 
			((Btst 5) (self setCursor: 997 1))
			((== (User controls?) 0)
				(if (User canInput?)
					(self setCursor: 992 (HaveMouse))
				else
					(self setCursor: waitCursor (HaveMouse))
				)
			)
			((== theCursor waitCursor)
				(if (== curRoomNum 140)
					(self setCursor: 993 (HaveMouse))
				else
					(self setCursor: normalCursor (HaveMouse))
				)
			)
			(else (self setCursor: theCursor (HaveMouse)))
		)
		(return (if (and global74 global73) (-- global73) else 0))
	)
	
	(method (replay)
		(TheMenuBar draw:)
		(SL enable:)
		(SetMenu
			1282
			26
			(if (DoSound sndGET_AUDIO_CAPABILITY)
				{Turn Off}
			else
				{Turn On}
			)
		)
		(super replay:)
	)
	
	(method (newRoom newRoomNumber param2)
		(if
			(or
				(and
					(not
						(OneOf
							newRoomNumber
							200
							203
							210
							213
							216
							220
							230
							235
							240
							245
							250
							253
							300
							305
							310
						)
					)
					(OneOf
						curRoomNum
						200
						203
						210
						213
						216
						220
						230
						235
						240
						245
						250
						253
						300
						305
						310
					)
				)
				(and
					(not (OneOf newRoomNumber 400 410 415 416 420 460))
					(OneOf curRoomNum 400 410 415 416 420 460)
				)
				(and
					(not (OneOf newRoomNumber 360 370 375 380 390))
					(OneOf curRoomNum 360 370 375 380 390)
				)
				(and
					(not (OneOf newRoomNumber 510 520 523 540 550))
					(OneOf curRoomNum 510 520 523 540 550)
				)
				(and
					(not (OneOf newRoomNumber 610 620 630 640 650))
					(OneOf curRoomNum 610 620 630 640 650)
				)
			)
			(gTheMusic fade:)
		)
		(Bclr 3)
		(Bclr 5)
		(cls)
		(Load rsFONT bigFont)
		(Load rsFONT userFont)
		(Load rsFONT smallFont)
		(Load rsFONT 999)
		(Load rsCURSOR normalCursor)
		(Load rsCURSOR waitCursor)
		(Load rsCURSOR 666)
		(Load rsCURSOR 992)
		(super newRoom: newRoomNumber)
		(= global71 0)
		(if (< argc 2)
			(= showStyle (Random 0 5))
		else
			(= showStyle param2)
		)
	)
	
	(method (startRoom param1)
		(LoadMany 0 993 991 988 981 973 971 969 967 21 50 51)
		(DisposeScript 958)
		(if debugOn (= debugOn 0) (SetDebug))
		(orchidSeconds stop: number: 1)
		(super startRoom: param1 &rest)
		(if
		(and (not (OneOf param1 530 260 420)) global64)
			(curRoom setLocales: 20)
		)
		(if (Btst 14) (curRoom setLocales: 22))
		(if (>= param1 200) (curRoom setRegions: 950))
		(cond 
			(
				(OneOf
					param1
					200
					203
					210
					213
					216
					220
					230
					235
					240
					245
					250
					253
					300
					305
					310
				)
				(curRoom setLocales: 299)
			)
			((OneOf param1 360 370 375 380 390) (curRoom setLocales: 399))
			((OneOf param1 400 410 415 416 420 460) (curRoom setLocales: 499))
			((OneOf param1 510 520 523 540 550) (curRoom setLocales: 599))
			((OneOf param1 610 620 630 640 650) (curRoom setLocales: 699))
		)
		(if (or (== gCurRoomNum 11) (== global66 708))
			(ego baseSetter: NormalBase)
		)
	)
	
	(method (changeScore param1)
		(= score (+ score param1))
		(if (> param1 0) (scoreSound playMaybe:))
	)
	
	(method (handleEvent event &tmp [temp0 2] temp2 [temp3 3] [temp6 50])
		(super handleEvent: event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(cond 
			((Said '--invalid--,--invalid--')
				(if (and (not musicLoop) (== global66 700))
					(Print {Easter Egg. Mode Larry Bizarre Activate})
					(= global66 820)
					(ego view: 820)
				)
			)
			((Said '--invalid--/bamboo')
				(if musicLoop
					(Print
						{Bambu Map: UP, UP, RIGHT, RIGHT, UP, LEFT, UP, LEFT, UP, UP, UP, LEFT, LEFT, DOWN, LEFT, LEFT, UP, UP, LEFT, UP.}
					)
				)
			)
			((Said 'ascot/backdrop')
				(if (= global64 (^ global64 $0001))
					(Print 0 14)
				else
					(Print 0 15)
				)
			)
			(
			(or (Said 'caress/ginsu') (Said 'sharpen/ginsu'))
				(cond 
					((not (ego has: 2)) (DontHave))
					((== (Ginsu_Knife_____ view?) 21) (ItIs))
					((!= curRoomNum 250) (Print 0 16))
				)
			)
			(
			(or (Said 'backdrop/*/bottle') (Said 'fill/bottle')) (Print 0 17))
			((Said '(drain<out),drain/beer,bottle')
				(cond 
					((not (ego has: 13)) (DontHave))
					((not musicLoop) (Print 0 18))
					((== (Bottle_of_Wine_ view?) 28) (Print 0 19))
					(else
						(Bottle_of_Wine_ view: 28)
						(Format (Bottle_of_Wine_ name?) 0 20)
						(Print 0 21)
					)
				)
			)
			((Said 'carve,carve>')
				(cond 
					((not (ego has: 2)) (Print 0 22))
					((== (Ginsu_Knife_____ view?) 2) (Print 0 23))
					((Said '[/!*]') (Print 0 24))
					((Said '/blade') (Print 0 25))
					((not (Said '/carving,granadilla')) (Print 0 26))
					((not (ego has: 3)) (Print 0 27))
					(
						(or
							(== (Granadilla_Wood____ view?) 22)
							(== (Granadilla_Wood____ view?) 34)
						)
						(Print 0 28)
					)
					((or (== gCurRoomNum 0) (== gCurRoomNum 10)) (Ok) (theGame setScript: (ScriptID 43)))
					(else (GoodIdea))
				)
				(event claimed: 1)
			)
			(
				(or
					(Said 'use/flower/lei<make')
					(Said 'weave,make/flower,lei')
				)
				(cond 
					((not (ego has: 11)) (Print 0 29))
					((== (some_Orchids__ view?) 26) (Print 0 30))
					((!= gCurRoomNum 0) (GoodIdea))
					(else (Ok) (theGame setScript: (ScriptID 42)))
				)
			)
			(
			(Said 'wear,(alter<in),(backdrop<on)/flower,lei')
				(cond 
					((not (ego has: 11)) (Print 0 31))
					((!= (some_Orchids__ view?) 26) (Print 0 32))
					((!= gCurRoomNum 0) (GoodIdea))
					(else (Print 0 33))
				)
			)
			(
				(or
					(Said 'use/blade/skirt<make')
					(Said 'weave,make/blade,skirt')
				)
				(cond 
					((not (ego has: 4)) (Print 0 34))
					((== (Native_Grass_ view?) 23) (Print 0 35))
					((!= gCurRoomNum 0) (GoodIdea))
					(else (Ok) (theGame setScript: (ScriptID 44)))
				)
			)
			(
				(or
					(Said 'get/naked')
					(Said '(get<off),drain/cloth,cloth')
				)
				(Print 0 36)
			)
			((Said 'alter/cloth,cloth') (Print 0 37))
			(
			(Said 'wear,(alter<in),(backdrop<on)/blade,skirt')
				(cond 
					((not (ego has: 4)) (Print 0 31))
					((not (== (Native_Grass_ view?) 23)) (Print 0 38))
					(else (Print 0 39))
				)
			)
			((and (ego has: 5) (Said 'use/soap')) (Print 0 40))
			(
				(or
					(Said 'unknownnumber/')
					(Said '/unknownnumber')
					(Said '//unknownnumber')
				)
				(Print 0 41)
			)
			(
				(and
					(ego has: 10)
					(not (ego has: 9))
					(Said 'look,look/decree,document,document')
				)
				(ego get: 9)
				(theGame changeScore: 100)
				(Print 0 42)
			)
			(
				(and
					(ego has: 9)
					(or
						(Said 'look<back/keycard,card')
						(Said 'look/back/keycard,card')
						(Said 'drag/keycard,card')
						(Said 'look/combination/keycard,card')
					)
				)
				(Ok)
				(theGame setScript: (ScriptID 45))
			)
			((Said 'count>')
				(= temp2 (inventory saidMe:))
				(cond 
					((Said '[/!*]') (Print 0 43))
					((not temp2) (event claimed: 1) (Printf 0 44 (Random 10 999)))
					((!= (inventory indexOf: temp2) 6) (Print 0 45))
					((not (temp2 ownedBy: ego)) (Print 0 46))
					(else (Printf 0 47 global94))
				)
			)
			((or (Said 'give/bill,buck') (Said 'bribe'))
				(cond 
					((not (ego has: 6)) (Print 0 48))
					((== (A_Twenty_Dollar_Bill_____ view?) 24)
						(Print 0 49 #icon (A_Twenty_Dollar_Bill_____ view?) 0 0)
						(Print 0 50 #at -1 144)
					)
					((== (A_Twenty_Dollar_Bill_____ view?) 25)
						(Print 0 51 #icon (A_Twenty_Dollar_Bill_____ view?) 0 0)
						(Print 0 52)
					)
					(else
						(Print 0 53 #icon (A_Twenty_Dollar_Bill_____ view?) 0 0)
						(Print 0 54)
					)
				)
			)
			(
				(and
					(ego has: 4)
					(== (Native_Grass_ view?) 23)
					(or
						(Said 'get/dress')
						(Said 'get<dress')
						(Said
							'wear,alter,(get<off),drain,(backdrop<on)/skirt,cloth,cloth'
						)
					)
				)
				(Print 0 55)
			)
			((and (ego has: 16) (Said 'backdrop//(bra)>'))
				(= temp2 (inventory saidMe:))
				(cond 
					((Said '[/!*]') (Print 0 56))
					((not temp2) (event claimed: 1) (Print 0 57))
					((not (temp2 ownedBy: ego)) (DontHave))
					(else (Print 0 58 #icon 16 0 0) (Print 0 59 #at -1 144))
				)
			)
			(
				(and
					(ego has: 2)
					(or (Said 'use/ginsu') (Said 'attack'))
				)
				(Print 0 60)
			)
			((Said 'open,(look<in)>')
				(= temp2 (inventory saidMe:))
				(cond 
					((Said '[/!*]') (Print 0 61))
					((not temp2) (event claimed: 1) (Print 0 57))
					((not (temp2 ownedBy: ego)) (Print 0 62))
					(else
						(switch (inventory indexOf: temp2)
							(11 (Print 0 63 #icon 11 0 0))
							(13
								(switch (Bottle_of_Wine_ view?)
									(28 (Print 0 64 #icon 28 0 0))
									(29 (Print 0 65 #icon 29 0 0))
									(else 
										(Print 0 66 #icon 13 0 0)
									)
								)
							)
							(14 (Print 0 67 #icon 14 0 0))
							(15 (Print 0 68 #icon 15 0 0))
							(16 (Print 0 69 #icon 16 0 0))
							(17 (Print 0 70 #icon 17 0 0))
							(19 (Print 0 71 #icon 19 0 0))
							(else 
								(Print 0 62)
								(LogPragFail)
							)
						)
					)
				)
			)
			((Said 'hello') (Print 0 72))
			((or (Said '/bye') (Said 'bye')) (Print 0 15))
			((Said 'thank') (Print 0 73))
			((Said 'knock')
				(Print 0 74)
				(Print (Format @temp6 0 75 global82) #at -1 144)
			)
			((Said 'attack') (Print 0 60))
			(
				(or
					(Said 'go/bathroom')
					(Said 'leak')
					(Said 'get/leak')
				)
				(if musicLoop
					(Print 0 76)
					(Print 0 77 #at -1 144)
				else
					(Print 0 78)
				)
			)
			((Said 'climb>')
				(cond 
					((Said '/wall,building') (Print 0 79))
					(musicLoop (Print 0 80))
					(else (Print 0 81))
				)
				(event claimed: 1)
			)
			((or (Said '//larry') (Said '/larry'))
				(cond 
					((not musicLoop) (Print 0 82))
					((< curRoomNum 590) (Print 0 83))
					(else (Print 0 84))
				)
			)
			((Said 'jump,dance') (Print 0 85))
			((Said 'sing') (Print 0 86))
			((Said 'delay') (Print 0 87))
			((Said 'pick') (Print 0 88))
			((Said 'holler') (Print 0 89))
			((Said 'rob') (Print 0 90))
			((or (Said 'n') (Said 'affirmative')) (Ok))
			((Said 'borrow') (Print 0 91))
			((Said 'cheat') (Print 0 92) (Print 0 93 #at -1 144) (= quit 1))
			((Said '(backdrop<on),wear>')
				(cond 
					((Said '[/!*]') (Print 0 94))
					((= temp2 (inventory saidMe:))
						(cond 
							((not (ego has: (inventory indexOf: temp2))) (DontHave))
							(
								(or
									(== temp2 16)
									(== temp2 17)
									(== temp2 14)
									(== temp2 15)
								)
								(YouAre)
							)
							(else (Print 0 95))
						)
					)
					(else (Print 0 96) (event claimed: 1))
				)
			)
			((Said 'backdrop>')
				(cond 
					((Said '[/!*]') (Print 0 97))
					((= temp2 (inventory saidMe:))
						(if (not (ego has: (inventory indexOf: temp2)))
							(DontHave)
						else
							(Print 0 98)
						)
					)
					(else (Print 0 99) (event claimed: 1))
				)
			)
			((Said 'throw>')
				(cond 
					((Said '[/!*]') (Print 0 100))
					((= temp2 (inventory saidMe:))
						(if (not (ego has: (inventory indexOf: temp2)))
							(DontHave)
						else
							(Print 0 101)
						)
					)
					(else (Print 0 102) (event claimed: 1))
				)
			)
			((Said 'smell')
				(Print 0 103)
				(cond 
					((Btst 8) (Print 0 104))
					((Btst 10) (Print 0 105))
					((Btst 62) (Print 0 106))
				)
			)
			((Said 'whistle') (Print 0 107))
			((Said 'ask/job') (Print 0 108) (Print 0 109 #at -1 144))
			(
			(and musicLoop (or (Said '/arnold') (Said '//arnold'))) (Print 0 110))
			((Said 'laugh') (Print 0 111))
			((Said 'eat') (Print 0 112))
			((Said 'lie,lie') (Print 0 113))
			((Said 'aid/i,self') (Print 0 114))
			((Said 'aid') (Print 0 115))
			((Said 'explore>')
				(if (Said '/cloth,panties,entertainer,larry')
					(Print 0 116)
					(inventory showSelf: ego)
					Inv
				else
					(event claimed: 1)
					(Print 0 117)
				)
			)
			((or (Said '/bang/ya') (Said 'bang/ya')) (Print 0 118))
			((Said 'bang/*') (Print 0 119))
			(
			(or (Said 'caress/i,larry,self') (Said 'jack')) (Print 0 120))
			((Said 'caress,caress,embrace,look/clit') (Printf 0 121 global82))
			((Said 'eat/babe') (Print 0 122))
			(
			(or (Said 'look<in/blouse') (Said 'look<up/skirt')) (Print 0 123))
			((Said 'drink') (Print 0 124))
			((or (Said 'use/key') (Said 'unbolt')) (Print 0 125))
			((or (Said '/book') (Said '//book')) (Print 0 126))
			((Said 'hear') (Print 0 127))
			((Said 'embrace') (Print 0 128))
			(
				(or
					(Said 'eat,bang/i')
					(Said 'crap,leak,bang,fart')
					(Said '/clit,crap,leak,bang,fart,asshole')
					(Said '//clit,crap,leak,bang,fart,asshole')
				)
				(Print 0 129)
			)
			((or (Said '/hell') (Said '//hell')) (Print 0 130))
			((Said 'look>')
				(cond 
					(
					(Said '/self,larry,entertainer,i,blouse,cloth,panties') (if (not musicLoop) (Print 0 131) else (Print 0 132)))
					((and (== (ego view?) 718) (Said '/sunglass')) (Print 0 133))
					((Said '/ankle,sandal')
						(if (== (ego view?) 718)
							(Print 0 134)
						else
							(Print 0 135)
						)
					)
					((Said '/bush,palm') (Print 0 136))
					((Said '/man,babe,couple') (Print 0 137))
					((Said '/wall,building') (Print 0 138))
					((Said '/carpet,down') (Print 0 139))
					((Said '/air,ceiling') (Print 0 140))
					((Said '/boob') (if musicLoop (Print 0 141) else (Print 0 142)))
					((Said '/ass') (if musicLoop (Print 0 143) else (Print 0 144)))
					((Said '<in/cup') (Print 0 145))
					((= temp2 (inventory saidMe:))
						(if (temp2 ownedBy: ego)
							(temp2 showSelf:)
						else
							(Print 0 62)
						)
					)
					(else
						(switch (Random 42 44)
							(42 (Print 0 146))
							(43 (Print 0 147))
							(44 (Print 0 62))
						)
						(event claimed: 1)
						(LogPragFail)
					)
				)
			)
			(
			(or (Said 'use,buy/bill,tips,buck') (Said 'buy')) (if (ego has: 6) (Print 0 148) else (Print 0 149)))
			((Said 'use>')
				(= temp2 (inventory saidMe:))
				(event claimed: 0)
				(cond 
					((Said '[/!*]') (Print 0 150))
					((not temp2) (Print 0 57))
					((not (temp2 ownedBy: ego)) (DontHave))
					(else (Print 0 151) (LogPragFail))
				)
				(event claimed: 1)
			)
			((Said 'give>')
				(= temp2 (inventory saidMe:))
				(event claimed: 0)
				(cond 
					((Said '/*[/!*]') (Print 0 152))
					((Said '[/!*]') (Print 0 153))
					((not temp2) (Print 0 154))
					((not (temp2 ownedBy: ego)) (DontHave))
					(else (Print 0 155) (LogPragFail))
				)
				(event claimed: 1)
			)
			((Said 'get<down') (Print 0 156))
			((Said 'get<up') (Print 0 157))
			((Said 'get>')
				(cond 
					((Said '[/!*]') (Print 0 158))
					(
						(and
							(= temp2 (inventory saidMe:))
							(temp2 ownedBy: ego)
						)
						(Print 0 159)
					)
					((== temp2 3) (Print 0 160))
					(else
						(switch (Random 33 35)
							(33 (Print 0 161))
							(34 (Print 0 162))
							(35 (Print 0 163))
						)
						(LogPragFail)
					)
				)
				(event claimed: 1)
			)
			((= temp2 (inventory saidMe:))
				(if (not (temp2 ownedBy: ego))
					(DontHave)
				else
					(Print 0 164)
				)
			)
			((Said 'address>')
				(if (Said '[/!*]')
					(Print 0 152)
				else
					(Print 0 165)
					(Print 0 109 #at -1 144)
					(event claimed: 1)
				)
			)
			(
				(or
					(Said '/clit,crap,leak,bang,asshole,boob,ass,asshole')
					(Said '//clit,crap,leak,bang,asshole,boob,ass,asshole')
				)
				(Print 0 166)
			)
			(
			(or (Said '//babe') (Said '/babe/') (Said '/babe')) (Print 0 167))
		)
	)
	
	(method (wordFail param1 &tmp [temp0 50])
		(switch (Random 0 4)
			(0
				(Print (Format @temp0 0 3 param1))
			)
			(1
				(Print (Format @temp0 0 4 param1))
			)
			(2
				(Print (Format @temp0 0 5 param1))
			)
			(3
				(Print (Format @temp0 0 6 param1))
			)
			(else 
				(Print (Format @temp0 0 7 param1))
			)
		)
	)
	
	(method (syntaxFail &tmp [temp0 40])
		(switch (Random 0 2)
			(0 (Print 0 8))
			(1 (Print 0 9))
			(else  (Print 0 10))
		)
	)
	
	(method (pragmaFail &tmp [temp0 40])
		(switch (Random 0 2)
			(0 (Print 0 11))
			(1 (Print 0 12))
			(else  (Print 0 13))
		)
	)
)

(instance Nothing of Iitem
	(properties)
)

(instance Credit_Card_______ of Iitem
	(properties
		view 1
		name "Credit Card_______"
	)
	
	(method (saidMe)
		(return
			(if (ego has: 9)
				(return 0)
			else
				(return (Said '/card[<credit]'))
			)
		)
	)
)

(instance Ginsu_Knife_____ of Iitem
	(properties
		said '/ginsu'
		view 2
		name "Ginsu Knife_____"
	)
)

(instance Granadilla_Wood____ of Iitem
	(properties
		view 3
		name "Granadilla Wood____"
	)
	
	(method (saidMe)
		(if (== view 22)
			(Said '/granadilla,carving')
			(return)
		else
			(Said '/granadilla')
			(return)
		)
	)
)

(instance Native_Grass_ of Iitem
	(properties
		owner 230
		view 4
		name "Native Grass_"
	)
	
	(method (saidMe)
		(if (== view 23)
			(Said '/skirt')
			(return)
		else
			(Said '/blade')
			(return)
		)
	)
)

(instance Soap of Iitem
	(properties
		said '/soap'
		owner 253
		view 5
	)
)

(instance A_Twenty_Dollar_Bill_____ of Iitem
	(properties
		view 6
		name "A Twenty Dollar Bill_____"
	)
	
	(method (saidMe)
		(cond 
			((== view 24) (Said '/buck') (return))
			((== view 25) (Said '/buck,tips,jar') (return))
			(else (Said '/buck,bill') (return))
		)
	)
)

(instance Land_Deed of Iitem
	(properties
		said '/deed'
		view 7
		name "Land Deed"
	)
)

(instance Beach_Towel____ of Iitem
	(properties
		said '/towel'
		view 8
		name "Beach Towel____"
	)
)

(instance Spa_Keycard___ of Iitem
	(properties
		said '/keycard,card'
		view 9
		name "Spa Keycard___"
	)
)

(instance Divorce_Decree of Iitem
	(properties
		said '/decree,decree'
		view 10
		name "Divorce Decree"
	)
)

(instance some_Orchids__ of Iitem
	(properties
		owner 235
		view 11
		name "some Orchids__"
	)
	
	(method (saidMe)
		(if (== view 26)
			(Said '/flower,lei')
			(return)
		else
			(Said '/flower')
			(return)
		)
	)
)

(instance Penthouse_Key__ of Iitem
	(properties
		said '/key'
		owner 450
		view 12
		name "Penthouse Key__"
	)
)

(instance Bottle_of_Wine_ of Iitem
	(properties
		view 13
		name "Bottle of Wine_"
	)
	
	(method (saidMe)
		(cond 
			((== view 28) (Said '/bottle') (return))
			((== view 29) (Said '/bottle,water') (return))
			(else (Said '/bottle,beer') (return))
		)
	)
)

(instance Panties of Iitem
	(properties
		said '/panties'
		owner 484
		view 14
	)
)

(instance Hose of Iitem
	(properties
		said '/hose'
		owner 484
		view 15
	)
)

(instance Bra of Iitem
	(properties
		said '/bra'
		owner 484
		view 16
	)
)

(instance Dress of Iitem
	(properties
		said '/dress'
		owner 484
		view 17
	)
)

(instance Magic_Marker____ of Iitem
	(properties
		said '/marker'
		view 18
		name "Magic Marker____"
	)
)

(instance Coconut of Iitem
	(properties
		said '/coconut'
		owner 530
		view 19
	)
)

(instance Marijuana of Iitem
	(properties
		said '/dope,dope'
		owner 530
		view 20
	)
	
	(method (saidMe)
		(if (== view 27)
			(Said '/dope,hemp')
			(return)
		else
			(Said '/dope')
			(return)
		)
	)
)

(instance statusCode of Code
	(properties)
	
	(method (doit param1)
		(Format
			param1
			0
			168
			gScore
			0
			169
			(if musicLoop
				{Passionate Patti PnC}
			else
				{Leisure Suit Larry 3 PnC}
			)
		)
	)
)

(instance ego of Ego
	(properties
		y 1111
		view 700
	)
)

(instance scoreSound of Sound
	(properties
		number 1
		priority 10
	)
)

(instance theMusic of Sound
	(properties
		number 1
	)
)

(instance theSoundFX of Sound
	(properties
		number 1
		priority 5
	)
)

(instance theWindow of SysWindow
	(properties)
	
	(method (open)
		(if (< (Graph grGET_COLOURS) 9)
			(if (or (< color 7) (== color 8))
				(= color 0)
				(= back 15)
			else
				(= color 15)
				(= back 0)
			)
		)
		(super open:)
	)
)

(instance NormalBase of Code
	(properties)
	
	(method (doit &tmp temp0)
		(if (== curRoomNum 253) (= temp0 22) else (= temp0 10))
		(ego brBottom: (+ (ego y?) 1))
		(ego brTop: (- (ego brBottom?) (ego yStep?)))
		(ego brLeft: (- (ego x?) temp0))
		(ego brRight: (+ (ego x?) temp0))
	)
)
