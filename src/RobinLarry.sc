;;; Sierra Script 1.0 - (do not remove this comment)
(script# 80)
(include sci.sh)
(use Main)
(use Intrface)
(use Game)

(public
	rm80 0
)

(instance rm80 of Locale
	(properties)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(cond 
			((Said '/hair') (Print 80 0))
			((Said '/entertainer')
				(Print 80 1)
				(if (>= global88 3) (Print 80 2 #at -1 144))
			)
			((Said 'give') (Printf 80 3 global82))
			(
			(or (Said '//buffet,buffet') (Said '/buffet,buffet')) (Print 80 4))
			((Said '/man') (Print 80 5) (Print 80 6 #at -1 144))
			((Said '/eye') (Print 80 7))
		)
	)
)
