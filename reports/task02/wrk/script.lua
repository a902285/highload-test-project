request = function()
  wrk.headers["Authorization"] = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTgyOTIzOTYxLCJleHAiOjE1ODI5Mjc1NjF9.ktP9uL0sAe2Ji04_UmeBC6PheDb7PkaGL7_OkhvleEktVzonFiHdPZW-cmOQB6dxNFV8n-Mz2rvuH2-sGylSnw"
  names = { "Андр",
            "Степ",
            "Вади",
            "Стани",
            "Оле",
            "Евген",
            "Анто",
            "Ил",
            "Арт",
            "Юр",
            "Бор",
            "Вла",
            "Его",
            "Макар",
            "Леон",
            "Серг",
            "Ники",
            "Анато",
            "Макс",
            "Эду",
            "Алекс",
            "Иг",
            "Лу",
            "Арк",
            "Конст",
            "Один",
            "Лебе",
            "Симо",
            "Сми",
            "Поно",
            "Шир",
            "Кула",
            "Дмит",
            "Бел",
            "Семе"}
  idx = math.random(1,35)
  path = "/api/v1/account/?limit=100&name=" .. url_encode(names[idx])
  return wrk.format("GET", path)
end

function url_encode(str)
   if str then
      str = str:gsub("\n", "\r\n")
      str = str:gsub("([^%w %-%_%.%~])", function(c)
         return ("%%%02X"):format(string.byte(c))
      end)
      str = str:gsub(" ", "+")
   end
   return str
end