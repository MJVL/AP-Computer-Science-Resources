; GetParent 1.1
; GetParent 1.0, Copyright 2005 sunjammer
; Macro and define added by John T. Haller of PortableApps.com, Copyright 2007
; http://nsis.sourceforge.net/Get_parent_directory
;
; === LICENSE ===
;This software is provided 'as-is', without any express or implied warranty. In no event will the authors be held liable for any damages arising from the use of this software.
;
;Permission is granted to anyone to use this software for any purpose, including commercial applications, and to alter it and redistribute it freely, subject to the following restrictions:;
;
;1. The origin of this software must not be misrepresented; you must not claim that you wrote the original software. If you use this software in a product, an acknowledgment in the product documentation would be appreciated but is not required.
;2. Altered source versions must be plainly marked as such, and must not be misrepresented as being the original software.
;3. This notice may not be removed or altered from any source distribution. 
;
; === USAGE ===
; GetParent
; input, top of stack  (e.g. C:\Program Files\Poop)
; output, top of stack (replaces, with e.g. C:\Program Files)
; modifies no other variables.
;
; Usage:
;   Push "C:\Program Files\Directory\Whatever"
;   Call GetParent
;   Pop $R0
;   ; at this point $R0 will equal "C:\Program Files\Directory"
 
Function GetParent
 
  Exch $R0
  Push $R1
  Push $R2
  Push $R3
  
  StrCpy $R1 0
  StrLen $R2 $R0
  
  loop:
    IntOp $R1 $R1 + 1
    IntCmp $R1 $R2 get 0 get
    StrCpy $R3 $R0 1 -$R1
    StrCmp $R3 "\" get
  Goto loop
  
  get:
    StrCpy $R0 $R0 -$R1
    
    Pop $R3
    Pop $R2
    Pop $R1
    Exch $R0
    
FunctionEnd

!macro _GetParent PARENTDIRECTORY CURRENTDIRECTORY
  Push "${CURRENTDIRECTORY}"
  Call GetParent
  Pop "${PARENTDIRECTORY}"
!macroend

!define GetParent '!insertmacro "_GetParent"'