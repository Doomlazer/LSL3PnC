;;; Sierra Script 1.0 - (do not remove this comment)
(script# 599)
(include sci.sh)
(use Main)
(use Intrface)
(use Game)
(use System)

(public
	rm599 0
)

(instance rm599 of Locale
	(properties)
	
	(method (init)
		(if (< global66 800) (= global66 800))
		(super init:)
		(if (not (OneOf prevRoomNum 510 520 523 540 550))
			(gTheMusic number: 599 loop: global72 play:)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(cond 
			((Said 'address/man,couple,babe') (Print 599 0))
			((Said 'use/coconut') (Print 599 1))
			((Said '/bra') (Print 599 2))
			((Said 'climb') (Print 599 3))
			((Said '/bush') (Print 599 4) (Print 599 5 #at -1 144))
			((Said '/carpet') (Print 599 6))
			(
				(and
					(ego has: 19)
					(or
						(Said 'drink/coconut')
						(Said 'drink/milk')
						(Said 'drink/coconut/milk')
					)
				)
				(Print 599 7)
				(Print 599 8 #at -1 144)
			)
			((Said 'look>')
				(cond 
					((Said '/up,air') (Print 599 9))
					((Said '/boulder') (Print 599 10))
					((Said '/carpet') (Print 599 11))
					((Said '/palm,bush') (Print 599 12))
				)
			)
		)
	)
)
