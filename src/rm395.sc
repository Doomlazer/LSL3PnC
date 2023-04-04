;;; Sierra Script 1.0 - (do not remove this comment)
(script# 395)
(include sci.sh)
(use Main)
(use Face)
(use Intrface)
(use Game)
(use User)
(use System)

(public
	rm395 0
)
(synonyms
	(babe bambi)
)

(local
	talkCount
	[str 55]
)
(instance rm395 of Rm
	(properties
		picture 395
	)
	
	(method (init)
		(super init:)
		(User canInput: 0 mapKeyToDir: 0)
		(self setRegions: 71 setLocales: 70 setScript: RoomScript)
		(NotifyScript 71 1 111 47)
		(NotifyScript 71 2 174 85)
		(NotifyScript 71 3 98 60)
		(NotifyScript 71 4 165 96)
		(NotifyScript 71 5 93 104)
		(NotifyScript 71 6 90 111)
	)
	
	(method (newRoom newRoomNumber)
		(super newRoom: newRoomNumber)
		(self dispose: 71)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(7
				(HandsOff)
				(EgoSays 395 53)
				(PersonSays 395 54)
				(AnimateFace 2 22)
				(= cycles 22)
			)
			(8
				(theGame changeScore: 99)
				(EgoSays 395 55)
				(PersonSays 395 56)
				(AnimateFace 2 22)
				(= cycles 22)
			)
			(9
				(EgoSays 395 57)
				(PersonSays 395 58)
				(AnimateFace 2 22)
				(= cycles 22)
				(gTheMusic fade:)
			)
			(10
				(= gCurRoomNum 1)
				(gTheMusic stop:)
				(curRoom newRoom: 390)
			)
			(11
				(EgoSays 395 59)
				(PersonSays 395 60)
				(AnimateFace 2 33)
				(= cycles 22)
			)
			(12 (curRoom newRoom: 390))
		)
	)
	
	(method (handleEvent event &tmp inventorySaidMe)
		(if
			(and
				(not (super handleEvent: event))
				(not (event claimed?))
				modelessDialog
				(or
					(and
						(== (event message?) KEY_RETURN)
						(== (event type?) evKEYBOARD)
					)
					(== (event type?) evMOUSEBUTTON)
				)
			)
			(event claimed: 1)
			(cls)
			(self cue:)
		)
		(if (event claimed?) (return))
		(cond 
			((== (event type?) evMOUSEBUTTON)
				(if (& (event modifiers?) emSHIFT)
					(switch theCursor
						(gTheCursor
							(theGame setCursor: 991 (HaveMouse))
							(event claimed: 1)
						)
						(991
							(theGame setCursor: 998 (HaveMouse))
							(event claimed: 1)
						)
						(999
							(theGame setCursor: 998 (HaveMouse))
							(event claimed: 1)
						)
						(993
							(theGame setCursor: 996 (HaveMouse))
							(event claimed: 1)
						)
						(996
							(theGame setCursor: 994 (HaveMouse))
							(event claimed: 1)
						)
						(998
							(theGame setCursor: 995 (HaveMouse))
							(event claimed: 1)
						)
						(995
							(theGame setCursor: 996 (HaveMouse))
							(event claimed: 1)
						)
						(994
							(if
								(or
									(== gTheCursor 900)
									(== gTheCursor 994)
									(== gTheCursor 666)
									(== gTheCursor 992)
									(== gTheCursor 993)
								)
								(theGame setCursor: 991 (HaveMouse))
							else
								(theGame setCursor: gTheCursor (HaveMouse))
								(= theCursor gTheCursor)
							)
							(event claimed: 1)
						)
					)
				else
					(if (== theCursor 999)
						(theGame setCursor: 998 (HaveMouse))
					)
					(if (proc0_27 1 319 21 190 event)
						(switch theCursor
							(998
								(Print 395 51)
								(PersonSays 395 52)
								(AnimateFace 2 33)
								(event claimed: 1)
							)
							(9
								(PersonSays 395 2)
								(AnimateFace 4)
								(event claimed: 1)
							)
							(5
								(PersonSays 395 3)
								(AnimateFace 4)
								(event claimed: 1)
							)
							(10
								(EgoSays 395 4)
								(PersonSays 395 5)
								(AnimateFace 4 6)
								(event claimed: 1)
							)
							(2
								(PersonSays 395 6)
								(AnimateFace 4)
								(event claimed: 1)
							)
							(11
								(PersonSays 395 7)
								(AnimateFace 4)
								(event claimed: 1)
							)
							(8
								(PersonSays 395 8)
								(AnimateFace 4)
								(event claimed: 1)
							)
							(994
								(Print 395 49)
								(PersonSays 395 50)
								(AnimateFace 6 12)
								(event claimed: 1)
							)
							(996
								(EgoSays 395 16)
								(PersonSays 395 17)
								(AnimateFace 4 11)
								(if (not currentEgo)
									(EgoSays 395 12)
									(PersonSays 395 13)
									(AnimateFace 4 22)
								else
									(self changeState: 7)
								)
								(event claimed: 1)
							)
							(991
								(HandsOff)
								(theGame setCursor: 999 (HaveMouse))
								(self changeState: 11)
								(event claimed: 1)
							)
							(else  (event claimed: 0))
						)
					)
				)
			)
			((Said 'give>')
				(= inventorySaidMe (inventory saidMe:))
				(event claimed: 0)
				(cond 
					((Said '[/!*]') (Print 395 0))
					((not inventorySaidMe) (Print 395 1))
					((not (inventorySaidMe ownedBy: ego)) (DontHave))
					((== inventorySaidMe (inventory at: 9)) (PersonSays 395 2) (AnimateFace 4))
					((== inventorySaidMe (inventory at: 5)) (PersonSays 395 3) (AnimateFace 4))
					((== inventorySaidMe (inventory at: 10)) (EgoSays 395 4) (PersonSays 395 5) (AnimateFace 4 6))
					((== inventorySaidMe (inventory at: 2)) (PersonSays 395 6) (AnimateFace 4))
					((== inventorySaidMe (inventory at: 11)) (PersonSays 395 7) (AnimateFace 4))
					((== inventorySaidMe (inventory at: 8)) (PersonSays 395 8) (AnimateFace 4))
					(else (PersonSays 395 9) (AnimateFace 4))
				)
				(event claimed: 1)
			)
			((Said 'bang') (Print 395 10) (PersonSays 395 11) (AnimateFace 4 4))
			((or (Said 'aid//tape') (Said 'aid/tape'))
				(if (not currentEgo)
					(EgoSays 395 12)
					(PersonSays 395 13)
					(AnimateFace 4 22)
				else
					(self changeState: 7)
				)
			)
			(
			(or (Said 'aid') (Said '/aid') (Said '//aid')) (if (Random 0 1) (Print 395 14) else (Print 395 15)))
			((or (Said 'ask//tape') (Said 'ask/tape')) (EgoSays 395 16) (PersonSays 395 17) (AnimateFace 4 11))
			(
				(or
					(Said 'class,(work<out)')
					(Said '/class,class,(work<out)')
					(Said '//class,class,(work<out)')
				)
				(EgoSays 395 18)
				(PersonSays 395 19)
				(AnimateFace 4 4)
			)
			(
				(or
					(Said '/tan,booth,booth')
					(Said '//tan,booth,booth')
				)
				(EgoSays 395 20)
				(PersonSays 395 21)
			)
			((or (Said '/attendant') (Said '//attendant')) (EgoSays 395 22) (PersonSays 395 23) (AnimateFace 6 5))
			((Said 'show/ball') (EgoSays 395 24) (PersonSays 395 25) (AnimateFace 2 20))
			((Said 'make/joke')
				(EgoSays 395 26)
				(EgoSays 395 27 67 -1 144)
				(PersonSays 395 28)
				(AnimateFace 7 22)
			)
			(
				(or
					(Said '(out<go),dance,show,date/')
					(Said 'get/babe/building')
					(Said
						'ask,go//area,backdrop,comedian,club,date,exit,bar,show,dance'
					)
					(Said
						'ask,go/area,backdrop,comedian,club,date,exit,bar,show,dance'
					)
				)
				(EgoSays 395 29)
				(PersonSays 395 30)
				(AnimateFace 4 11)
			)
			((Said '/equipment,camera,camera') (EgoSays 395 31) (PersonSays 395 32) (AnimateFace 2 20))
			((Said 'address/babe')
				(cond 
					((not currentEgo)
						(EgoSays (Format @str 395 33 global171))
						(PersonSays 395 34)
					)
					((Btst 8)
						(Printf 395 33 global171)
						(PersonSays 395 35)
						(AnimateFace 4 11)
					)
					((Btst 10)
						(Printf 395 33 global171)
						(PersonSays 395 36)
						(AnimateFace 4 11)
					)
					((Btst 62)
						(Printf 395 33 global171)
						(PersonSays 395 37)
						(AnimateFace 4 11)
					)
					(else
						(AnimateFace 2 20)
						(switch (++ talkCount)
							(1
								(EgoSays 395 38)
								(PersonSays 395 32)
							)
							(2
								(EgoSays 395 39)
								(PersonSays 395 17)
							)
							(else 
								(EgoSays 395 40)
								(PersonSays 395 41)
							)
						)
					)
				)
			)
			((or (Said 'enjoy/ya') (Said '/enjoy/ya')) (EgoSays 395 42) (PersonSays 395 43) (AnimateFace 5 44))
			(
				(or
					(Said 'cease/look')
					(Said 'look<cease')
					(Said 'look/area')
					(Said 'done,exit,exit,done,exit,go')
					(Said 'nightstand,(nightstand<up),(get<off,up)')
				)
				(self changeState: 11)
			)
			((Said 'look>')
				(cond 
					((Said '/calf') (PersonSays 395 44) (AnimateFace 3 22))
					((Said '/bracelet') (Print 395 45))
					((Said '/ear') (Print 395 46))
					((or (Said '/tape') (Said '//tape')) (EgoSays 395 47) (PersonSays 395 48))
					((Said '/boob') (Print 395 49) (PersonSays 395 50) (AnimateFace 6 12))
					((Said '[/area,babe]') (Print 395 51) (PersonSays 395 52) (AnimateFace 2 33))
				)
			)
		)
	)
)
