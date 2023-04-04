;;; Sierra Script 1.0 - (do not remove this comment)
(script# 266)
(include sci.sh)
(use Main)
(use Intrface)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm266 0
)

(local
	local0
)
(instance rm266 of Rm
	(properties
		picture 266
	)
	
	(method (init)
		(super init:)
		(self setScript: RoomScript)
		(if (< global88 3) (addToPics add: atpBikiniTop doit:))
		(addToPics add: atpBikiniBottom doit:)
		(User canInput: 0 mapKeyToDir: 0)
		(HandsOn)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (== theCursor 999)
			(theGame setCursor: 998 (HaveMouse))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Btst 32)) (= seconds 3))
			)
			(1
				(Bset 32)
				(Print 266 10)
				(Print 266 11 #at -1 144)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			(
				(or
					(Said 'cease/look')
					(Said 'look<cease')
					(Said 'look/beach,area')
					(Said 'exit,done')
					(Said 'exit,exit,done,exit,go')
				)
				(Ok)
				(curRoom newRoom: 260)
			)
			((Said 'address,ask,say') (Print 266 0) (curRoom newRoom: 265))
			((Said 'give') (Print 266 1))
			((Said 'look>')
				(cond 
					((Said '/babe,body,maller') (Print 266 2) (Print 266 3))
					((Said '/boob') (if (>= global88 3) (Print 266 4) else (Print 266 5)))
					((Said '/ass,bottom') (Print 266 6))
					((Said '/clit,ball') (Print 266 7))
					((Said '/eye,eye') (Print 266 0) (curRoom newRoom: 265))
					((Said '/calf') (Print 266 8))
					((Said '[/area]') (Print 266 9))
				)
			)
			((== (event type?) evMOUSEBUTTON)
				(cond 
					((& (event modifiers?) emSHIFT)
						(event claimed: 1)
						(switch theCursor
							(gTheCursor
								(theGame setCursor: 991 (HaveMouse))
								(event claimed: 1)
							)
							(991
								(theGame setCursor: 998 (HaveMouse))
								(event claimed: 1)
							)
							(992
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
							(else  (event claimed: 0))
						)
					)
					(
						(and
							(> (event x?) 83)
							(< (event x?) 141)
							(> (event y?) 9)
							(< (event y?) 180)
						)
						(event claimed: 1)
						(switch theCursor
							(998
								(Print 266 0)
								(theGame setCursor: 998)
								(curRoom newRoom: 265)
							)
							(996 (Print 266 1))
							(995
								(if (>= global88 3) (Print 266 4) else (Print 266 5))
							)
							(991
								(Ok)
								(HandsOff)
								(= theCursor 998)
								(theGame setCursor: 998)
								(curRoom newRoom: 260)
							)
							(else  (event claimed: 0))
						)
					)
				)
			)
		)
	)
)

(instance atpBikiniTop of PV
	(properties
		y 56
		x 98
		view 266
	)
)

(instance atpBikiniBottom of PV
	(properties
		y 91
		x 103
		view 266
		cel 1
	)
)
