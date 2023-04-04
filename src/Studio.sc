;;; Sierra Script 1.0 - (do not remove this comment)
(script# 699)
(include sci.sh)
(use Intrface)
(use Game)

(public
	rm699 0
)

(instance rm699 of Locale
	(properties)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(cond 
			((Said 'address/man,couple,babe') (Print 699 0))
			((Said 'drain,(get<off)/sandal') (Print 699 1))
			((Said '(look<up),look[/ceiling]') (Print 699 2))
			((Said 'look/building,area') (Print 699 3))
		)
	)
)
