;;; Sierra Script 1.0 - (do not remove this comment)
(script# 40)
(include sci.sh)
(use Main)
(use Intrface)
(use DCIcon)
(use Motion)
(use System)

(public
	DyingScript 0
)

(instance DyingScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 133])
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_0042
			pushi    0
			callb    HandsOff,  0
			pushi    #fade
			pushi    0
			lag      gTheMusic
			send     4
			pushi    #setScript
			pushi    1
			pushi    0
			lag      curRoom
			send     6
			ldi      1002
			sag      gCurRoomNum
			pushi    2
			pushi    132
			pushi    2
			callk    Load,  4
			ldi      3
			aTop     seconds
			jmp      code_0101
code_0042:
			dup     
			ldi      1
			eq?     
			bnt      code_0101
			pushi    2
			pushi    128
			pTos     caller
			callk    Load,  4
			pushi    #stop
			pushi    0
			lag      orchidSeconds
			send     4
			pushi    #number
			pushi    1
			pushi    2
			pushi    6
			pushi    1
			pushi    1
			pushi    63
			pushi    1
			pushi    65535
			pushi    42
			pushi    0
			lag      gTheMusic
			send     22
			pushi    #view
			pushi    1
			pTos     caller
			lofsa    deadIcon
			send     6
			pushi    13
			pTos     register
			pushi    80
			pTos     next
			pushi    33
			lsg      bigFont
			pushi    82
			lofss    deadIcon
			pushi    81
			lofss    {Keep On Muddling}
			pushi    0
			pushi    81
			lofss    {Order A Hintbook}
			pushi    1
			calle    Print,  26
			bnt      code_00a7
			pushi    2
			pushi    40
			pushi    0
			calle    Print,  4
code_00a7:
			ldi      1
			bnt      code_0101
			pushi    15
			pushi    40
			pushi    1
			pushi    80
			lofss    {Al says:}
			pushi    33
			lsg      bigFont
			pushi    81
			lofss    {Restore}
			pushi    1
			pushi    81
			lofss    {Restart}
			pushi    2
			pushi    81
			lofss    {Quit}
			pushi    3
			calle    Print,  30
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_00e2
			pushi    #restore
			pushi    0
			lag      theGame
			send     4
			jmp      code_00fe
code_00e2:
			dup     
			ldi      2
			eq?     
			bnt      code_00f2
			pushi    #restart
			pushi    0
			lag      theGame
			send     4
			jmp      code_00fe
code_00f2:
			dup     
			ldi      3
			eq?     
			bnt      code_00fe
			ldi      1
			sag      quit
			jmp      code_0101
code_00fe:
			toss    
			jmp      code_00a7
code_0101:
			toss    
			ret     
		)
	)
)

(instance deadIcon of DCIcon
	(properties)
	
	(method (init)
		(super init:)
		(if (== curRoomNum 540)
			((= cycler (End new:)) init: self)
		)
	)
)
