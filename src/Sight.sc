;;; Sierra Script 1.0 - (do not remove this comment)
(script# 982)
(include sci.sh)
(use Main)

(public
	IsOffScreen 0
	CantBeSeen 1
	AngleDiff 2
)

(procedure (IsOffScreen param1)
	(return
		(not
			(if (if (< 0 (param1 x?)) (< (param1 x?) 320))
				(if
					(if (< 0 (- (param1 y?) (param1 z?)))
						(< (- (param1 y?) (param1 z?)) 200)
					)
					1
				)
			else
				0
			)
		)
	)
)

(procedure (CantBeSeen param1 theTheEgo param3 param4 &tmp theEgo temp1 temp2 temp3 temp4 theEgoX theEgoY)
	(= theEgo theTheEgo)
	(= temp1 param3)
	(= temp2 param4)
	(if (< argc 4)
		(= temp2 32767)
		(if (< argc 3)
			(if (< argc 2) (= theEgo ego))
			(= temp1
				(- 360 (if (== theEgo ego) (* 2 egoBlindSpot) else 0))
			)
		)
	)
	(= temp3 (param1 x?))
	(= temp4 (param1 y?))
	(= theEgoX (theEgo x?))
	(= theEgoY (theEgo y?))
	(return
		(if
			(and
				(!= param1 theEgo)
				(or
					(<
						(/ temp1 2)
						(Abs
							(AngleDiff
								(GetAngle theEgoX theEgoY temp3 temp4)
								(theEgo heading?)
							)
						)
					)
					(<
						temp2
						(GetDistance theEgoX theEgoY temp3 temp4 perspective)
					)
				)
			)
			1
		else
			0
		)
	)
)

(procedure (AngleDiff param1 param2)
	(if (>= argc 2) (= param1 (- param1 param2)))
	(return
		(cond 
			((<= param1 -180) (+ param1 360))
			((> param1 180) (- param1 360))
			(else param1)
		)
	)
)
