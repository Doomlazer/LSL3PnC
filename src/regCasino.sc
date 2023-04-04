;;; Sierra Script 1.0 - (do not remove this comment)
(script# 417)
(include sci.sh)
(use Main)
(use Intrface)
(use Game)

(public
	regCasino 0
)

(instance regCasino of Rgn
	(properties)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(cond 
			((or (Said 'gamble/game') (Said 'gamble')) (Print 417 0) (Print 417 1 #at -1 144))
			((and global64 (Said 'increase/backstage')) (Printf 417 2 (++ gameMinutes)))
			((and global64 (Said 'increase/document')) (Printf 417 3 (++ global98)))
			((and global64 (Said 'increase/attorney')) (Printf 417 4 (++ lastSysGlobal)))
			((Said 'look>')
				(cond 
					(
					(Said '/gambler,entertainer,stair,couple,man,babe') (Print 417 5))
					((Said '/rail') (Print 417 6))
					((Said '/door') (Print 417 7))
					((Said '/mirror')
						(Print 417 8)
						(if (not (Btst 12))
							(Bset 12)
							(theGame changeScore: 2)
						)
					)
					((Said '[/area]') (Print 417 9))
				)
			)
		)
	)
)
