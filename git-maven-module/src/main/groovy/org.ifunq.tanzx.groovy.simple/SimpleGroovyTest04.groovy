package org.ifunq.tanzx.groovy.simple

def collection = [1, "tzx", 2L, true];
collection << false
def map = ["id": 1, "name": "tcy", "isMan": false];
println(collection[1])
println(collection[-1])
println(map["id"])
println(map.name)

map.father = 'tanxianbiao'
println map['father']

map = map+[son:'tanzongxi'];
println(map.son)