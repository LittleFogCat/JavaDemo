local lib = require("top.littlefogcat.demolist.luaj.base.lib")

lib.foo1()
lib.foo2()

local http = require("top.littlefogcat.demolist.luaj.base.http")
local response = http:get("http://www.baidu.com")
print(response)