;;; Sierra Script 1.0 - (do not remove this comment)
(script# 265)
(include sci.sh)
(use Main)
(use Face)
(use Intrface)
(use Game)
(use Invent)
(use User)
(use System)

(public
	rm265 0
)
(synonyms
	(babe maller)
)

(local
	local0
)
(instance rm265 of Rm
	(properties
		picture 265
	)
	
	(method (init)
		(if (ego has: 1) (Load rsVIEW 1))
		(super init:)
		(self setRegions: 71 setLocales: 70 setScript: RoomScript)
		(NotifyScript 71 1 115 77)
		(NotifyScript 71 2 171 82)
		(NotifyScript 71 3 114 82)
		(NotifyScript 71 4 169 87)
		(NotifyScript 71 5 139 121)
		(NotifyScript 71 6 140 136)
		(= theCursor 998)
		(theGame setCursor: 998)
		(ego get: 1)
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
			(1
				(HandsOff)
				(User canInput: 1 canControl: 1)
				(= theCursor 997)
				(theGame setCursor: 997)
				(EgoSays 265 62 82 1 0 0)
				(theGame changeScore: 50)
				((Inv at: 1) owner: -1)
				(PersonSays 265 63)
				(= gCurRoomNum 1)
				(++ state)
				(AnimateFace 2 99)
				(= cycles 50)
			)
			(2
				(Print 265 64)
				(AnimateFace 2 55)
				(= cycles 20)
			)
			(3
				(curRoom newRoom: 260)
				(= theCursor 997)
				(theGame setCursor: 997)
			)
		)
	)
	
	(method (handleEvent event &tmp inventorySaidMe)
		(if (event claimed?) (return))
		(cond 
			(
				(or
					(Said 'go,get//center,shopping')
					(Said 'go,get/shopping,center')
				)
				(EgoSays 265 0)
				(PersonSays 265 1)
			)
			(
				(or
					(Said '(go<out),date/entertainer')
					(Said 'make/enjoy,date')
					(Said 'let<make/enjoy,date')
					(Said '/make/enjoy')
					(Said
						'ask,get,go/area,backdrop,comedian,club,date,exit,bar,show,building,dance'
					)
					(Said
						'ask,get,go//area,backdrop,comedian,club,date,exit,bar,show,building,dance'
					)
				)
				(EgoSays 265 2)
				(PersonSays 265 3)
			)
			((Said 'sell') (PersonSays 265 4) (AnimateFace 2 20))
			((Said '/souvenir') (EgoSays 265 5) (PersonSays 265 6))
			((Said '/swim,bay,water') (EgoSays 265 7) (PersonSays 265 8))
			(
				(or
					(Said 'cease/look')
					(Said 'look<cease')
					(Said 'nightstand,(nightstand<up),(get<off,up)')
					(Said 'look/beach,area')
					(Said 'done,exit,exit,done,exit,go')
				)
				(self changeState: 2)
			)
			((Said 'bang') (PersonSays 265 9 filthStr) (AnimateFace 4 8))
			((Said 'show/ball') (EgoSays 265 10) (PersonSays 265 11) (AnimateFace 2 20))
			((Said '/weather') (PersonSays 265 12))
			((Said 'make/joke')
				(Print 265 13)
				(Print 265 14 #at -1 144)
				(PersonSays 265 15)
				(AnimateFace 7 22)
			)
			((Said 'enjoy/ya') (EgoSays 265 16) (PersonSays 265 17) (AnimateFace 5 44))
			(
				(or
					(Said '(out<go),dance,date/')
					(Said 'get/babe/building')
					(Said 'ask/babe/date')
					(Said
						'go/area,backdrop,comedian,club,date,exit,bar,show,dance'
					)
				)
				(EgoSays 265 18)
				(PersonSays 265 19)
			)
			((Said '/center') (EgoSays 265 20) (PersonSays 265 21))
			((Said 'address')
				(switch (Random 1 3)
					(1
						(EgoSays 265 22)
						(PersonSays 265 23)
					)
					(2
						(EgoSays 265 24)
						(PersonSays 265 25)
					)
					(else 
						(EgoSays 265 26)
						(PersonSays 265 27)
					)
				)
			)
			((Said 'look>')
				(cond 
					((Said '/calf') (Print 265 28) (PersonSays 265 29) (AnimateFace 3 22))
					((Said '/skin') (Print 265 30) (Print 265 31 #at -1 144))
					((Said '/boob')
						(if (>= global88 3)
							(Print 265 32)
							(PersonSays 265 33)
						else
							(Print 265 34)
							(PersonSays 265 35)
						)
						(AnimateFace 6 12)
					)
					((Said '/eye') (Print 265 36))
					((Said '/nose') (Print 265 37))
					((Said '/ear') (Print 265 38) (Print 265 39 #at -1 144))
					((Said '/lip') (Print 265 40))
					((Said '/dicklicker') (Print 265 41))
					((Said '/eye') (Print 265 42))
					((Said '/hair') (Print 265 43))
					((Said '/ass,bottom') (Print 265 44))
					((Said '/clit') (Print 265 45) (PersonSays 265 46) (AnimateFace 6 12))
					((Said '[/area,beach,babe]')
						(Print 265 47)
						(Print 265 48 #at -1 144)
						(AnimateFace 2 33)
					)
				)
			)
			((Said 'show>')
				(= inventorySaidMe (inventory saidMe:))
				(event claimed: 0)
				(cond 
					((Said '[/!*]') (Print 265 49))
					(
						(or
							(not inventorySaidMe)
							(not (inventorySaidMe ownedBy: ego))
						)
						(DontHave)
					)
					((== inventorySaidMe (inventory at: 3)) (PersonSays 265 50) (AnimateFace 4))
					((== inventorySaidMe (inventory at: 5)) (PersonSays 265 51) (AnimateFace 4))
					((== inventorySaidMe (inventory at: 11)) (PersonSays 265 52) (AnimateFace 4))
					((== inventorySaidMe (inventory at: 1)) (PersonSays 265 53))
					(else (PersonSays 265 54))
				)
				(event claimed: 1)
			)
			((Said 'give>')
				(= inventorySaidMe (inventory saidMe:))
				(event claimed: 0)
				(cond 
					((Said '[/!*]') (Print 265 55))
					((not inventorySaidMe) (Print 265 56))
					((not (inventorySaidMe ownedBy: ego)) (DontHave))
					((== inventorySaidMe (inventory at: 3)) (PersonSays 265 57) (AnimateFace 4))
					((== inventorySaidMe (inventory at: 5)) (PersonSays 265 58) (AnimateFace 4))
					((== inventorySaidMe (inventory at: 11)) (PersonSays 265 59) (AnimateFace 4))
					((== inventorySaidMe (inventory at: 1))
						(if (not (ego has: 1))
							(DontHave)
						else
							(self changeState: 1)
						)
					)
					(else (PersonSays 265 60))
				)
				(event claimed: 1)
			)
			((Said 'get') (PersonSays 265 61) (AnimateFace 2 22))
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
					(if (== theCursor 995)
						(theGame setCursor: 995 (HaveMouse))
					)
					(if
						(and
							(or
								(proc0_27 99 132 65 88 event)
								(proc0_27 153 195 75 90 event)
							)
							(== theCursor 998)
						)
						(Print 265 36)
						(event claimed: 1)
					)
					(if
					(and (proc0_27 126 153 77 117 event) (== theCursor 998))
						(Print 265 37)
						(event claimed: 1)
					)
					(if
					(and (proc0_27 72 86 74 104 event) (== theCursor 998))
						(Print 265 38)
						(Print 265 39 #at -1 144)
						(event claimed: 1)
					)
					(if (proc0_27 118 165 128 142 event)
						(switch theCursor
							(998
								(Print 265 40)
								(event claimed: 1)
							)
							(996
								(switch (Random 1 3)
									(1
										(EgoSays 265 22)
										(PersonSays 265 23)
									)
									(2
										(EgoSays 265 24)
										(PersonSays 265 25)
									)
									(3
										(EgoSays 265 26)
										(PersonSays 265 27)
									)
								)
								(event claimed: 1)
							)
						)
					)
					(if
						(or
							(proc0_27 57 231 21 59 event)
							(proc0_27 241 219 21 185 event)
						)
						(switch theCursor
							(998
								(Print 265 43)
								(event claimed: 1)
							)
						)
					)
					(if
						(and
							(proc0_27 55 240 20 190 event)
							(== (event claimed?) 0)
						)
						(switch theCursor
							(999 (event claimed: 1))
							(998
								(Print 265 47)
								(Print 265 48 #at -1 144)
								(AnimateFace 2 33)
								(event claimed: 1)
							)
							(995
								(PersonSays 265 61)
								(AnimateFace 2 22)
								(event claimed: 1)
								(theGame setCursor: 995 (HaveMouse))
							)
							(996
								(switch (Random 1 3)
									(1
										(EgoSays 265 22)
										(PersonSays 265 23)
									)
									(2
										(EgoSays 265 24)
										(PersonSays 265 25)
									)
									(3
										(EgoSays 265 26)
										(PersonSays 265 27)
									)
								)
								(event claimed: 1)
							)
							(3
								(PersonSays 265 57)
								(AnimateFace 4)
								(event claimed: 1)
							)
							(5
								(PersonSays 265 58)
								(AnimateFace 4)
								(event claimed: 1)
							)
							(9
								(PersonSays 265 59)
								(AnimateFace 4)
								(event claimed: 1)
							)
							(1
								(if (not (ego has: 1))
									(DontHave)
									(event claimed: 1)
								else
									(= gTheCursor 900)
									(theGame setCursor: 997 (HaveMouse))
									(HandsOff)
									(theGame setCursor: 997 (HaveMouse))
									(self changeState: 1)
									(theGame setCursor: 997 (HaveMouse))
								)
							)
							(991
								(HandsOff)
								(self changeState: 2)
								(event claimed: 1)
							)
							(else 
								(PersonSays 265 60)
								(event claimed: 1)
							)
						)
					)
					(if
						(and
							(proc0_27 0 320 21 190 event)
							(== (event claimed?) 0)
						)
						(switch theCursor
							(995 (event claimed: 1))
							(999 (event claimed: 1))
							(991
								(self changeState: 2)
								(event claimed: 1)
							)
							(else 
								(theGame setCursor: 991 (HaveMouse))
								(event claimed: 1)
							)
						)
					)
				)
			)
		)
	)
)
