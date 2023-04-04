;;; Sierra Script 1.0 - (do not remove this comment)
(script# 260)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm260 0
)
(synonyms
	(maller babe)
	(man man)
)

(local
	puttingDownTowel
	noSwimMsg
	vendorHere
	[local3 2]
	[msgBuf 40]
	[titleBuf 22]
)
(instance rm260 of Rm
	(properties
		picture 260
		horizon 66
		west 250
	)
	
	(method (init &tmp temp0)
		(super init:)
		(User canInput: 0 mapKeyToDir: 0)
		(self setScript: RoomScript)
		(if (and (Btst 23) (== global96 0))
			(= global96 1)
			(= debugging 267)
		)
		(if
			(and
				(ego has: 3)
				(< global96 6)
				(== global66 707)
				(== gCurRoomNum 10)
				(or
					(== ((inventory at: 3) view?) 22)
					(== ((inventory at: 3) view?) 34)
				)
			)
			(= global96 5)
		)
		(if (== gCurRoomNum 1)
			(= global96 3)
			(= debugging 264)
		)
		(if (< prevRoomNum curRoomNum)
			(ego posn: 1 174 loop: 0)
		)
		(NormalEgo)
		(switch global96
			(0
				(Load rsVIEW 712)
				(aLizard init:)
			)
			(1 (ego observeControl: 16384))
			(2
				(aTawni setLoop: 2 setCel: 255)
				(TawniScript changeState: 8)
				(ego observeControl: 16384)
				(-- global96)
			)
			(3
				(Load rsVIEW (- 263 (* 5 (>= global88 3))))
				(Load rsVIEW 264)
				(Load rsSOUND 8)
				(Load rsSOUND 9)
				(Load rsSOUND 260)
				(Load rsSOUND 261)
				(aTawni
					illegalBits: 0
					ignoreActors:
					view: (- 263 (* 5 (>= global88 3)))
					loop: 0
					cel: 0
				)
				(ego
					illegalBits: 0
					ignoreActors:
					view: global66
					loop: 0
					posn: 132 165
					setMotion: MoveTo 138 165
				)
				(RoomScript changeState: 29)
			)
			(4
				(aTawni init:)
				(ego observeControl: 16384)
			)
			(5
				(Load rsVIEW 707)
				(Load rsVIEW 22)
				(Load rsVIEW 34)
				(HandsOff)
				(Bset 5)
				(= debugging 707)
				(= gCurRoomNum 10)
				(aTawni init:)
			)
			(6
				(= debugging -1)
				(aTowel init:)
			)
			(7
				(aTowel init: hide:)
				(= debugging -1)
			)
		)
		(if (and (not musicLoop) (>= global96 6))
			(Load rsVIEW (+ 701 currentEgo))
			(Load rsVIEW 261)
			(Load rsVIEW 709)
			(aLizard init:)
		)
		(if (!= global96 5) (ego init:))
		(if (and global96 (< global96 6))
			(aTowel init:)
			(aTawni init:)
			(if (== global96 3) (TawniScript changeState: 11))
		)
		(if
			(and
				debugging
				global96
				(not global97)
				(!= debugging -1)
				(< global96 6)
			)
			(aVendor init:)
		)
		(if (and (not global97) (!= global96 3))
			(gTheMusic number: 260 loop: -1 play:)
		)
		(if
			(and
				(== gCurRoomNum 14)
				(or (== prevRoomNum 266) (== prevRoomNum 265))
			)
			(= gCurRoomNum 0)
		)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) $0100)
			(if (not noSwimMsg)
				(= noSwimMsg 1)
				(Printf 260 0 global82)
			)
		else
			(= noSwimMsg 0)
		)
		(if (and (!= gCurRoomNum 0) (!= gCurRoomNum 1003))
			(ego observeControl: 512)
		)
		(if
		(and (== (ego onControl:) 512) (== gCurRoomNum 0))
			(self changeState: 5)
		)
	)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0)
			(5
				(= cycles (= seconds 0))
				(HandsOff)
				(= gCurRoomNum 1003)
				(ego
					view: (if musicLoop 812 else 712)
					illegalBits: -513
					cycleSpeed: 1
					moveSpeed: 1
					setStep: 2 2
					setCycle: Fwd
				)
				(= seconds 3)
			)
			(6
				(ego setMotion: 0 setCycle: End self)
			)
			(7 (ego hide:) (= seconds 2))
			(8
				(theGame setScript: (ScriptID 40))
				((ScriptID 40)
					caller: (+ 4 (ego view?))
					register: (Format @msgBuf 260 24 global82)
					next: (Format @titleBuf 260 25)
				)
			)
			(9
				(HandsOff)
				(ego setMotion: MoveTo 126 155 self)
			)
			(10
				(ego view: 709 setLoop: 0 cel: 0 setCycle: End self)
			)
			(11
				(aTowel hide:)
				(ego get: 8 setCycle: Beg self)
				(= global96 7)
				(theGame changeScore: 2)
			)
			(12
				(if puttingDownTowel
					(= puttingDownTowel 0)
					(self cue:)
				else
					(NormalEgo)
				)
			)
			(13
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 151 170 self)
				(LizardScript changeState: 8)
			)
			(14
				(ego
					view: 261
					setLoop: 0
					cel: 0
					setCycle: End self
					cycleSpeed: 1
				)
			)
			(15
				(aTowel view: 261 loop: 1 posn: 184 170 show: stopUpd:)
				(ego
					view: global66
					cycleSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 184 170 self
				)
			)
			(16
				(ego
					cycleSpeed: 1
					view: (+ 701 currentEgo)
					setLoop: 0
					cel: 0
					setCycle: End self
				)
			)
			(17
				(ego view: 261 setLoop: 2 cel: 0 setCycle: End self)
				(LizardScript changeState: 9)
			)
			(18
				(= gCurRoomNum 1005)
				(User canInput: 1)
				(= seconds 5)
			)
			(19
				(Print 260 26)
				(= seconds 5)
			)
			(20
				(Print 260 27)
				(= seconds 5)
			)
			(21
				(if (Btst 11)
					(Print 260 28)
					(= seconds 4)
				else
					(Bset 11)
					(theGame changeScore: 30)
					(Print 260 29)
					(= seconds 10)
				)
			)
			(22
				(Print 260 30)
				(theGame setScript: (ScriptID 40))
				((ScriptID 40)
					caller: 259
					register: (Format @msgBuf 260 31)
					next: (Format @titleBuf 260 32)
				)
			)
			(23
				(HandsOff)
				(Ok)
				(= seconds 0)
				(ego view: 261 setCycle: Beg self)
			)
			(24
				(ego
					view: (+ 701 currentEgo)
					setLoop: 0
					setCel: 255
					setCycle: Beg self
				)
			)
			(25
				(ego
					cycleSpeed: 0
					view: global66
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 151 169 self
				)
			)
			(26
				(ego
					cycleSpeed: 1
					view: 709
					setLoop: 0
					cel: 0
					setCycle: End self
				)
			)
			(27
				(aTowel hide:)
				(ego setCycle: Beg self)
			)
			(28
				(NormalEgo)
				(= gCurRoomNum 0)
			)
			(29
				(HandsOff)
				(Bset 5)
				(= seconds 0)
				(aTawni setScript: 0 setCycle: End self)
				(gTheMusic number: 8 loop: -1 play:)
			)
			(30
				(aTawni hide:)
				(ego
					posn: 153 165
					view: (- 263 (* 5 (>= global88 3)))
					loop: 1
					cel: 0
					setCycle: End self
					cycleSpeed: 1
				)
			)
			(31
				(ego loop: 2 cel: 0 setCycle: End self)
			)
			(32
				(ego viewer: humpCycler setLoop: 3 setCycle: Fwd)
			)
			(33
				(Print 260 33)
				(Print 260 34)
				(Print 260 35)
				(gTheMusic number: 9 loop: 1 play:)
				(ego
					viewer: 0
					cycleSpeed: 1
					loop: 4
					cel: 0
					setCycle: End self
				)
			)
			(34
				(Print 260 36)
				(= seconds 3)
			)
			(35
				(Print 260 37)
				(Print 260 38)
				(= seconds 3)
			)
			(36
				(Print 260 39)
				(Print 260 40 #at -1 144)
				(= seconds 3)
			)
			(37
				(Print 260 41)
				(Print 260 42)
				(if (>= global88 3) (Print 260 43))
				(Print 260 44)
				(= seconds 3)
			)
			(38
				(Print 260 45)
				(Print 260 46)
				(ego get: 2)
				(= global98 1)
				(= gameMinutes 0)
				(theGame changeScore: 40)
				(= seconds 3)
			)
			(39
				(Print 260 47)
				(Print 260 48)
				(Print 260 49)
				(VendorScript changeState: 10)
				(= seconds 3)
			)
			(40 (ego setCycle: Beg self))
			(41
				(Print 260 50)
				(= seconds 3)
			)
			(42
				(Print 260 51)
				(gTheMusic number: 8 loop: -1 play:)
				(ego viewer: humpCycler setLoop: 3 setCycle: Fwd)
				(= seconds 12)
			)
			(43
				(gTheMusic fade:)
				(Print 260 52)
				(gTheMusic number: 261 loop: -1 play:)
				(Print 260 53 #at -1 10)
				(ego
					viewer: 0
					cycleSpeed: 0
					loop: 4
					cel: 0
					setCycle: End self
				)
			)
			(44
				(aTawni view: 262 loop: 2 setCel: 255 show:)
				(ego loop: 5 cel: 0 setCycle: End self)
			)
			(45
				(Print 260 54 #at -1 10)
				(ego
					posn: 142 165
					setLoop: 6
					setCycle: Fwd
					setStep: 1 1
					setMotion: MoveTo 127 165
				)
				(= cycles 44)
			)
			(46
				(Print (Format @msgBuf 260 55 filthStr) #at -1 10)
				(ego cycleSpeed: 1 loop: 7 cel: 0 setCycle: End self)
			)
			(47
				(Print 260 56 #at -1 10)
				(ego viewer: humpCycler loop: 8 setCycle: Fwd)
				(= seconds 3)
			)
			(48
				(Print 260 57 #at -1 10)
				(= cycles 11)
			)
			(49
				(aTawni view: 262 setScript: TawniScript)
				(TawniScript changeState: 9)
				(ego viewer: 0 loop: 9 cel: 0 setCycle: End self)
			)
			(50
				(Print 260 58 #at -1 10)
				(= seconds 3)
			)
			(51
				(gTheMusic number: 9 loop: 1 play: self)
				(Print 260 59)
				(NormalEgo 2)
				(Bclr 5)
				(ego observeControl: 16384)
				(= global96 4)
				(= gCurRoomNum 0)
			)
			(52
				(gTheMusic number: 260 loop: -1 play:)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (super handleEvent: event))
				(not (event claimed?))
				modelessDialog
				(== (event message?) KEY_RETURN)
				(== (event type?) evKEYBOARD)
				(== (event type?) evMOUSEBUTTON)
			)
			(event claimed: 1)
			(cls)
			(self cue:)
		)
		(if (event claimed?) (return))
		(cond 
			((Said 'carve,carve') (Print 260 1))
			((Said 'get/crab') (Print 260 2))
			(
				(or
					(Said '(get<on),throw,use,lie,bang/towel')
					(Said '(bath<air),sunbath')
					(Said 'get/tan,booth')
					(Said '/(bath<air),sunbath')
					(Said 'drain/cloth')
					(Said 'lie,bang/down[<on]/towel')
				)
				(cond 
					((== gCurRoomNum 1005) (YouAre))
					((== gCurRoomNum 10) (Print 260 3))
					((!= gCurRoomNum 0) (GoodIdea))
					((== global96 1) (Print 260 4))
					((== global96 4) (Print 260 5) (Print 260 6))
					((== global96 6) (Ok) (= puttingDownTowel 1) (self changeState: 9))
					((not (ego has: 8)) (DontHave))
					(else (Ok) (self changeState: 13))
				)
			)
			(
				(or
					(Said 'nightstand,(nightstand<up),(get<off),(get<up)')
					(Said 'sunbath<cease')
					(Said 'exit/towel')
				)
				(if (!= gCurRoomNum 1005)
					(Print 260 7)
				else
					(self changeState: 23)
				)
			)
			((Said '/towel>')
				(cond 
					((ego has: 8) (event claimed: 0))
					(
						(or
							(& (aTowel signal?) $0080)
							(not (cast contains: aTowel))
						)
						(Print 260 8)
						(Print 260 9)
						(event claimed: 1)
					)
					((Said 'get,grab,rob,(pick<up)')
						(cond 
							((== gCurRoomNum 10) (Print 260 3))
							((!= gCurRoomNum 0) (GoodIdea))
							((== global96 1) (Print 260 10))
							((== global96 4) (Print 260 11) (Print 260 6))
							((!= global96 6) (Print 260 7))
							(else (Ok) (self changeState: 9))
						)
					)
					((Said 'look')
						(cond 
							((and (>= global96 1) (<= global96 5)) (Print 260 12))
							((< global96 6) (Print 260 13))
							((== global96 6) (Print 260 14))
							((== gCurRoomNum 1005) (Print 260 15))
							(else (event claimed: 0))
						)
					)
					(else (event claimed: 0))
				)
			)
			((Said 'look>')
				(cond 
					((Said '/boulder,boulder') (Print 260 16))
					((Said '/bay,water,bay') (Print 260 17) (Print 260 18))
					(
					(or (Said 'down<look') (Said '/beach,down,beach')) (Print 260 19))
					((Said '/crab') (Print 260 20))
					((Said '[/area]')
						(if (and global96 (< global96 5))
							(Print 260 21)
						else
							(Print 260 22)
						)
						(Print 260 23)
					)
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
						(== gCurRoomNum 1005)
						(== theCursor 992)
					)
					(self changeState: 23)
				)
				(if
					(and
						(> (event x?) 35)
						(< (event x?) 140)
						(> (event y?) 129)
						(< (event y?) 172)
					)
					(event claimed: 1)
					(switch theCursor
						(8
							(cond 
								((== gCurRoomNum 1005) (YouAre))
								((== gCurRoomNum 10) (Print 260 3))
								((!= gCurRoomNum 0) (GoodIdea))
								((== global96 1) (Print 260 4))
								((== global96 4) (Print 260 5) (Print 260 6))
								((== global96 6) (Ok) (= puttingDownTowel 1) (self changeState: 9))
								((not (ego has: 8)) (DontHave))
								(else (Ok) (self changeState: 13))
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(proc0_26 aTawni (event x?) (event y?))
						(cast contains: aTawni)
					)
					(event claimed: 1)
					(switch theCursor
						(998
							(cond 
								((== 707 (ego view?)) (Print 260 77))
								((== global96 4) (Print 260 78))
								((== vendorHere 1) (Print 260 79))
								((not (& (ego onControl:) $1000)) (NotClose))
								(else
									(if (not (Btst 61)) (Printf 260 80 global171))
									(HandsOff)
									(theGame setCursor: 998)
									(= global96 2)
									(TawniScript changeState: 5)
								)
							)
						)
						(996
							(cond 
								((not (& (ego onControl:) $1000)) (NotClose))
								((== 707 (ego view?)) (Print 260 69) (Print 260 75))
								((== global96 4) (Print 260 69) (Print 260 70) (Print 260 71 #at -1 144))
								(else (Print 260 76))
							)
						)
						(3
							(Print 260 62)
							(Print 260 63)
						)
						(5
							(Print 260 64)
							(Print 260 64)
							(Print 260 65)
						)
						(11 (Print 260 66))
						(else  (event claimed: 0))
					)
				)
				(if (proc0_26 aTowel (event x?) (event y?))
					(event claimed: 1)
					(switch theCursor
						(998)
						(995
							(cond 
								((== gCurRoomNum 1005) (YouAre))
								((== gCurRoomNum 10) (Print 260 3))
								((!= gCurRoomNum 0) (GoodIdea))
								((== global96 1) (Print 260 4))
								((== global96 4) (Print 260 5) (Print 260 6))
								((== global96 6) (Ok) (= puttingDownTowel 1) (self changeState: 9))
								((not (ego has: 8)))
								(else (Ok) (self changeState: 13))
							)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 0)
						(< (event x?) 10)
						(> (event y?) 122)
						(< (event y?) 167)
					)
					(event claimed: 1)
					(switch theCursor
						(999
							(ego setMotion: MoveTo -4 148 self)
						)
						(else  (event claimed: 0))
					)
				)
				(if
					(and
						(> (event x?) 5)
						(< (event x?) 319)
						(> (event y?) 15)
						(< (event y?) 134)
					)
					(event claimed: 1)
					(switch theCursor
						(998
							(if (and global96 (< global96 5))
								(Print 260 21)
							else
								(Print 260 22)
							)
							(Print 260 23)
						)
						(else  (event claimed: 0))
					)
				)
			)
		)
	)
)

(instance aTawni of Act
	(properties
		y 165
		x 153
		view 262
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setScript: TawniScript ignoreActors:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return))
		(cond 
			((Said 'give,sell,show>')
				(cond 
					((not (& (ego onControl:) $1000)) (NotClose))
					((== 707 (ego view?))
						(= temp0 (inventory indexOf: gTheCursor))
						(event claimed: 0)
						(cond 
							((Said '[/!*]') (Print 260 60))
							((not temp0) (Print 260 61))
							((not (temp0 ownedBy: ego)) (DontHave))
							((== temp0 (inventory at: 3)) (Print 260 62) (Print 260 63))
							((== temp0 (inventory at: 5)) (Print 260 64) (Print 260 64) (Print 260 65))
							((== temp0 (inventory at: 11)) (Print 260 66))
							(else (Print 260 67))
						)
						(Print 260 68)
					)
					((== global96 4) (Print 260 69) (Print 260 70) (Print 260 71 #at -1 144))
					(else (Print 260 72))
				)
				(event claimed: 1)
			)
			((or (Said '//maller>') (Said '/maller>'))
				(cond 
					((> global96 4) (event claimed: 1) (Print 260 73))
					((Said 'bang') (Print 260 74))
					((Said 'address')
						(cond 
							((not (& (ego onControl:) $1000)) (NotClose))
							((== 707 (ego view?)) (Print 260 69) (Print 260 75))
							((== global96 4) (Print 260 69) (Print 260 70) (Print 260 71 #at -1 144))
							(else (Print 260 76))
						)
					)
					((Said 'look')
						(cond 
							((== 707 (ego view?)) (Print 260 77))
							((== global96 4) (Print 260 78))
							((== vendorHere 1) (Print 260 79))
							((not (& (ego onControl:) $1000)) (NotClose))
							(else
								(if (not (Btst 61)) (Printf 260 80 global171))
								(HandsOff)
								(Bset 5)
								(= global96 2)
								(TawniScript changeState: 5)
							)
						)
					)
				)
			)
		)
	)
)

(instance TawniScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 7)))
			(1
				(aTawni cycleSpeed: 1 setLoop: 1 setCycle: End)
				(= seconds (Random 1 3))
			)
			(2
				(aTawni setCycle: Beg)
				(= seconds (Random 3 5))
			)
			(3
				(aTawni setLoop: (Random 4 5) setCycle: End)
				(= seconds (Random 3 5))
			)
			(4
				(aTawni setCycle: Beg self)
				(= state -1)
			)
			(5
				(= seconds 0)
				(aTawni
					cycleSpeed: 2
					setLoop: 2
					cel: 0
					setCycle: End self
				)
				(if (>= global88 3) (++ state))
			)
			(6
				(aTawni setLoop: 3 cel: 0 setCycle: End self)
			)
			(7
				(if (== global96 2)
					(if (not (Btst 61)) (Bset 61) (Print 260 81))
					(= gCurRoomNum 14)
					(curRoom newRoom: 266)
				)
			)
			(8
				(= seconds 0)
				(if (>= global88 3)
					(self cue:)
				else
					(aTawni setCycle: Beg self)
				)
			)
			(9
				(aTawni setLoop: 2 setCel: 255 setCycle: Beg self)
				(= vendorHere 0)
				(= state 0)
			)
			(11 (= seconds 0))
		)
	)
)

(instance aVendor of Act
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: debugging
			setCycle: Walk
			setScript: VendorScript
		)
	)
)

(instance VendorScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aVendor posn: -15 123)
				(switch debugging
					(707 (self cue:))
					(264
						(if (<= global88 1) (= cycles 99) else (= cycles 222))
					)
					(else 
						(= seconds (Random 5 11))
					)
				)
			)
			(1
				(= vendorHere 1)
				(aVendor
					setLoop: 0
					view: debugging
					setMotion: MoveTo 137 149 self
				)
				(= cycles 11)
			)
			(2
				(Print 260 85)
				(= cycles 11)
			)
			(3
				(Print 260 85)
				(= cycles 11)
			)
			(4 (Print 260 86))
			(5
				(if
				(and (!= global96 3) (>= (TawniScript state?) 5))
					(return)
				)
				(Print 260 87)
				(aVendor setLoop: (- (NumLoops aVendor) 1))
				(if (== debugging 264)
					(aVendor setCycle: Fwd)
					(RoomScript changeState: 33)
					(return)
				)
				(aVendor viewer: salesViewer)
				(Print 260 88)
				(switch debugging
					(267 (Print 260 89))
					(268 (Print 260 90))
					(269 (Print 260 91))
					(707 (Print 260 92))
				)
				(TawniScript changeState: 5)
				(= cycles 30)
			)
			(6
				(switch debugging
					(267 (Print 260 93))
					(268
						(Print 260 94)
						(Print 260 95)
					)
					(269 (Print 260 96))
					(707
						(Print 260 97)
						(Print 260 98 #at -1 144)
					)
				)
				(= cycles 30)
			)
			(7
				(switch debugging
					(267 (Print 260 99))
					(268 (Print 260 100))
					(269 (Print 260 101))
					(707
						(if (== ((Inv at: 3) view?) 22)
							(Print 260 102 #icon 22 0 0)
						else
							(Print 260 103 #icon 34 0 0)
						)
					)
				)
				(= cycles 30)
			)
			(8
				(switch debugging
					(267 (Print 260 104))
					(268 (Print 260 105))
					(269
						(Print 260 106)
						(Print 260 107 #at -1 144)
					)
					(707 (Printf 260 108 filthStr))
				)
				(= cycles 30)
			)
			(9
				(aVendor loop: 0 setCycle: Walk viewer: 0)
				(switch debugging
					(267
						(Print 260 109)
						(Print 260 110)
						(Print 260 111)
					)
					(268 (Print 260 112))
					(269
						(Print 260 113)
						(Print 260 114)
					)
					(707
						(Print 260 115)
						(theGame changeScore: 35)
						(ego get: 6 put: 3 -1)
						(= global94 20)
						(Print 260 116)
					)
				)
				(TawniScript cue:)
				(= cycles 30)
			)
			(10
				(aVendor
					cycleSpeed: 0
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo -30 (Random 114 163) self
				)
			)
			(11
				(cond 
					((== debugging 707) (= debugging -1) (= global96 6) (curRoom newRoom: 250))
					((or (== debugging 269) (== debugging 264)) (= debugging -1))
					(else (++ debugging) (self changeState: 0))
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(cond 
			((Said 'look/man,man')
				(if (or (< state 1) (> state 10))
					(Print 260 73)
				else
					(Print 260 82)
				)
			)
			((Said 'address/man,man')
				(if (or (< state 1) (> state 10))
					(Print 260 73)
				else
					(Print 260 83)
					(Print 260 84)
				)
			)
		)
	)
)

(instance aTowel of View
	(properties
		y 169
		x 154
		view 262
	)
	
	(method (init)
		(super init:)
		(self setPri: 4 ignoreActors: stopUpd:)
	)
)

(instance aLizard of Act
	(properties
		view 260
		priority 15
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			setCycle: Walk
			setScript: LizardScript
			setPri: 15
			ignoreActors:
		)
	)
)

(instance LizardScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== state 5)
				(>= (RoomScript state?) 23)
				(<= (RoomScript state?) 28)
			)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Random 0 1)
					(aLizard setLoop: 0 posn: -20 (Random 172 188))
				else
					(aLizard setLoop: 1 posn: 218 198)
				)
				(= seconds (Random 10 20))
			)
			(1
				(aLizard
					setMotion: MoveTo (Random 126 192) (Random 172 188) self
				)
			)
			(2 (= seconds (Random 2 5)))
			(3
				(aLizard
					cycleSpeed: 1
					setLoop: (+ 2 (aLizard loop?))
					cel: 0
					setCycle: End self
				)
			)
			(4 (= cycles (Random 3 7)))
			(5
				(aLizard
					setLoop: (+ 2 (aLizard loop?))
					cel: 0
					setCycle: Fwd
				)
				(if
					(and
						(<= (RoomScript state?) 22)
						(>= (RoomScript state?) 18)
					)
					(= seconds 7)
				else
					(= cycles (Random 5 9))
				)
			)
			(6
				(aLizard
					setLoop: (- (aLizard loop?) 2)
					setCel: 255
					setCycle: Beg self
				)
			)
			(7
				(aLizard
					setLoop: (- (aLizard loop?) 2)
					cycleSpeed: 0
					setCycle: Walk
				)
				(if (== 0 (aLizard loop?))
					(aLizard setMotion: MoveTo 218 198 self)
				else
					(aLizard setMotion: MoveTo -55 (Random 172 188) self)
				)
				(= state -1)
			)
			(8
				(= seconds (= cycles 0))
				(aLizard
					setCycle: Walk
					setLoop: 1
					setMotion: MoveTo (- (aLizard x?) 33) 222
				)
			)
			(9
				(if (>= global88 3)
					(aLizard
						setLoop: 0
						posn: -20 180
						setCycle: Walk
						setMotion: MoveTo 148 188 self
					)
				)
			)
			(10
				(aLizard setMotion: MoveTo 192 167 self)
			)
			(11
				(aLizard
					cycleSpeed: 1
					setLoop: 2
					cel: 0
					setCycle: End self
				)
				(= state 3)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(if (and state (Said '/lizard>'))
			(cond 
				((Said 'get,grab/') (Print 260 117))
				((Said 'look/')
					(cond 
						((== state 0) (Print 260 73))
						((and (== state 5) (== gCurRoomNum 1005)) (Print 260 118))
						(else (Print 260 119))
					)
				)
				(else (Print 260 120) (event claimed: 1))
			)
		)
	)
)

(instance humpCycler of Code
	(properties)
	
	(method (doit)
		(cond 
			((<= global88 1) (ego setCycle: 0))
			((not (Random 0 9)) (ego cycleSpeed: (Random 0 5)))
		)
	)
)

(instance salesViewer of Code
	(properties)
	
	(method (doit)
		(if (not (Random 0 3))
			(aVendor setCel: (Random 0 (- (NumCels aVendor) 1)))
		)
	)
)
