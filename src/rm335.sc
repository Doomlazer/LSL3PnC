;;; Sierra Script 1.0 - (do not remove this comment)
(script# 335)
(include sci.sh)
(use Main)
(use Face)
(use Intrface)
(use Game)
(use Invent)
(use User)
(use System)

(public
	rm335 0
)
(synonyms
	(man man dale)
)

(local
	noWaterMsg
	noBraMsg
	noHoseMsg
)
(instance rm335 of Rm
	(properties
		picture 335
	)
	
	(method (init)
		(super init:)
		(User canInput: 0 mapKeyToDir: 0)
		(self setRegions: 71 setScript: RoomScript)
		(NotifyScript 71 1 154 78)
		(NotifyScript 71 2 188 81)
		(NotifyScript 71 3 152 78)
		(NotifyScript 71 4 187 81)
		(NotifyScript 71 5 170 106)
		(NotifyScript 71 6 168 114)
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
				(EgoSays 335 45)
				(PersonSays 335 46)
				(AnimateFace 2 55)
				(= cycles 50)
			)
			(2 (curRoom newRoom: 330))
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			(
				(or
					(Said '(go<out),date')
					(Said 'bang')
					(Said 'get/*/building')
					(Said 'ask/*/date')
					(Said 'ask,get/date')
					(Said '(let<make),make/enjoy')
					(Said '*/make/enjoy')
					(Said 'go/bed,penthouse')
					(Said 'give/job<blow')
					(Said 'give/blow<job')
					(Said 'give/head')
					(Said 'give/head<job')
					(Said 'eat,blow,eat,eat/man,ball,coconut,ball')
				)
				(PersonSays 335 0)
				(AnimateFace 4 4)
			)
			((Said '/bill,health') (Print 335 1))
			(
			(or (Said 'show/ball/entertainer') (Said 'show/ball')) (EgoSays 335 2) (EgoSays 335 3) (AnimateFace 2 20))
			(
				(or
					(Said 'aid')
					(Said '/aid')
					(Said '//larry')
					(Said '/larry')
				)
				(EgoSays 335 4)
				(PersonSays 335 5)
			)
			(
				(or
					(Said '/maze,bush,art,bamboo')
					(Said '//maze,bush,art,bamboo')
				)
				(PersonSays 335 6)
			)
			((Said '/bar,blackboard,drink,club,dance') (PersonSays 335 7))
			((Said 'give')
				(if (InRoom 14 330)
					(PersonSays 335 8)
				else
					(PersonSays 335 9)
				)
			)
			((Said 'address')
				(cond 
					((and (not noHoseMsg) (not (ego has: 15))) (= noHoseMsg 1) (PersonSays 335 10))
					((and (not noBraMsg) (not (ego has: 16))) (= noBraMsg 1) (PersonSays 335 11))
					(
						(and
							(not noWaterMsg)
							(or (not (ego has: 13)) (!= ((Inv at: 13) view?) 29))
						)
						(= noWaterMsg 1)
						(PersonSays 335 12)
					)
					(else (EgoSays 335 13) (PersonSays 335 14))
				)
			)
			((Said 'make/joke')
				(EgoSays 335 15)
				(EgoSays 335 16 67 -1 144)
				(PersonSays 335 17)
				(AnimateFace 7 22)
			)
			((Said 'enjoy/ya') (EgoSays 335 18) (PersonSays 335 19) (AnimateFace 5 44))
			(
				(or
					(Said 'cease/look')
					(Said 'look<cease')
					(Said 'look/area')
					(Said 'done,exit,exit,done,exit,go')
				)
				(self changeState: 1)
			)
			((Said 'embrace') (Print 335 20))
			((Said '/beard') (Print 335 21) (Print 335 22 #at -1 144))
			((Said '/ass') (Print 335 23))
			((Said '/bracelet') (Print 335 24))
			((Said '/cloth') (Print 335 25))
			((Said '/body') (Print 335 26))
			((Said '/ankle') (Print 335 27))
			((Said '/hair') (Print 335 28))
			((Said '/ear') (Print 335 29))
			((Said '/nose') (Print 335 30))
			((Said '/lip,lip') (Print 335 31))
			((Said '/eye') (Print 335 32))
			(
				(or
					(Said 'caress,grab,caress')
					(Said '/ball,ball,coconut')
				)
				(EgoSays 335 33)
				(PersonSays 335 34)
				(AnimateFace 5 44)
			)
			((Said '/dicklicker') (Print 335 35))
			((Said 'look>')
				(cond 
					((Said '/calf') (PersonSays 335 36) (AnimateFace 3 22))
					((Said '/bracelet') (Print 335 37))
					((Said '/ear') (Print 335 38))
					((Said '/boob') (Print 335 39) (PersonSays 335 40) (AnimateFace 6 12))
					((Said '[/area,man,man]') (Print 335 41) (PersonSays 335 42) (AnimateFace 2 33))
				)
			)
			(
				(or
					(Said '(out<go),dance,show,date/')
					(Said 'get/man/building')
					(Said 'ask/man/date')
					(Said
						'go/area,backdrop,comedian,club,date,exit,bar,show,dance'
					)
				)
				(EgoSays 335 43)
				(PersonSays 335 44)
			)
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
					(if
						(and
							(or
								(proc0_27 140 167 75 82 event)
								(proc0_27 180 200 75 83 event)
							)
							(== theCursor 998)
						)
						(Print 335 32)
						(event claimed: 1)
					)
					(if
					(and (proc0_27 164 181 83 100 event) (== theCursor 998))
						(Print 335 30)
						(event claimed: 1)
					)
					(if
					(and (proc0_27 115 130 70 96 event) (== theCursor 998))
						(Print 335 29)
						(event claimed: 1)
					)
					(if (proc0_27 153 185 108 122 event)
						(switch theCursor
							(998
								(Print 335 31)
								(event claimed: 1)
							)
							(996
								(switch (Random 1 3)
									(1
										(EgoSays 335 15)
										(EgoSays 335 16 67 -1 144)
										(PersonSays 335 17)
										(AnimateFace 7 22)
									)
									(2
										(EgoSays 335 18)
										(PersonSays 335 19)
										(AnimateFace 5 44)
									)
									(3
										(EgoSays 335 4)
										(PersonSays 335 5)
									)
								)
								(event claimed: 1)
							)
						)
					)
					(if (proc0_27 126 231 21 62 event)
						(switch theCursor
							(998
								(Print 335 28)
								(event claimed: 1)
							)
						)
					)
					(if
						(and
							(proc0_27 108 232 21 187 event)
							(== (event claimed?) 0)
						)
						(switch theCursor
							(994
								(cond 
									((and (not noHoseMsg) (not (ego has: 15))) (= noHoseMsg 1) (PersonSays 335 10))
									((and (not noBraMsg) (not (ego has: 16))) (= noBraMsg 1) (PersonSays 335 11))
									(
										(and
											(not noWaterMsg)
											(or (not (ego has: 13)) (!= ((Inv at: 13) view?) 29))
										)
										(= noWaterMsg 1)
										(PersonSays 335 12)
									)
									(else (EgoSays 335 13) (PersonSays 335 14))
								)
							)
							(991
								(HandsOff)
								(self changeState: 1)
							)
							(else  (event claimed: 0))
						)
					)
				)
			)
		)
	)
)
