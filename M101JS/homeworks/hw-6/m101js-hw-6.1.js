db.companies.aggregate( [
    { $match: { "relationships.person": { $ne: null } } },
    { $project: { relationships: 1, permalink: 1, _id: 0 } },
    { $unwind: "$relationships" },
    { $group: {
        _id: "$relationships.person",
        count: { $sum: 1 },
        companies: { $addToSet: "$permalink" }
    } },
    { $project: {
        _id: 1,
        number_of_companies: { $size: "$companies" }
    } },
    //{ $match: { "_id.permalink" :"roger-ehrenberg" } },
    //{ $match: { "_id.permalink" :"josh-stein" } },
    //{ $match: { "_id.permalink" :"tim-hanlon" } },
    { $match: { "_id.permalink" :"eric-di-benedetto" } },
    //{ $sort: { count: -1 } }
] )