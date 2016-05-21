db.companies.aggregate([{
    $match: { "founded_year": 2004 }
}, {
    $project: { 
        _id: 0,
        name: 1,
        permalink: 1,
        funding_rounds: 1,
        number_of_funding_rounds: { $size: "$funding_rounds" }
    }
}, {
    $match: { number_of_funding_rounds: { $gte: 5 } }
}, {
    $sort: { number_of_funding_rounds: -1 }
}]);