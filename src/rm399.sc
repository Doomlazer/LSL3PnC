;;; Sierra Script 1.0 - (do not remove this comment)
(script# 399)
(include sci.sh)
(use Main)
(use Intrface)
(use Game)
(use System)

(public
	rm399 0
)

(instance rm399 of Locale
	(properties)
	
	(method (init)
		(super init:)
		(if (not (OneOf prevRoomNum 360 370 375 380 390))
			(gTheMusic number: 399 loop: global72 play:)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(cond 
			((Said '/carpet') (Print 399 0))
			((Said '(look<up),look[/ceiling]') (Print 399 1))
			((Said '/wall') (Print 399 2))
			((Said '/door') (Printf 399 3 global82))
		)
	)
)
