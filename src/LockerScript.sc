;;; Sierra Script 1.0 - (do not remove this comment)
(script# 45)
(include sci.sh)
(use Main)
(use Intrface)
(use System)

(public
	LockerScript 0
)

(local
	[num1Buf 20]
	[num2Buf 20]
	[num3Buf 20]
)
(instance LockerScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (Btst 44)
					(self cue:)
				else
					(theGame changeScore: 65)
					(Print 45 0 #icon 9 0 1)
					(= seconds 2)
				)
			)
			(1
				(if (Btst 44)
					(Format @num1Buf 45 1 46 gameFlags)
					(Format @num2Buf 45 1 46 gGameFlags)
					(Format @num3Buf 45 1 46 gGameFlags_2)
					(Printf 45 2 @num1Buf @num2Buf @num3Buf)
				else
					(Bset 44)
					(Format @num1Buf 45 1 46 (= gameFlags 0))
					(while (== 32 (StrAt @num1Buf 0))
						(= gameFlags (Random 1 24))
						(Format @num1Buf 45 1 46 gameFlags)
					)
					(= gGameFlags gameFlags)
					(while
						(or
							(== gameFlags gGameFlags)
							(== 32 (StrAt @num2Buf 0))
						)
						(= gGameFlags (Random 1 24))
						(Format @num2Buf 45 1 46 gGameFlags)
					)
					(= gGameFlags_2 gameFlags)
					(while
						(or
							(== gameFlags gGameFlags_2)
							(== gGameFlags gGameFlags_2)
							(== 32 (StrAt @num3Buf 0))
						)
						(= gGameFlags_2 (Random 1 24))
						(Format @num3Buf 45 1 46 gGameFlags_2)
					)
					(Printf 45 3 @num1Buf @num2Buf @num3Buf)
				)
				(HandsOn)
				(theGame setScript: 0)
				(DisposeScript 45)
				(DisposeScript 46)
			)
		)
	)
)
