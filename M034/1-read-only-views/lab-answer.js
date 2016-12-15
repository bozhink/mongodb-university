db.createView('peopleSectors', 'people', [
    { $lookup: {
        from: 'companies',
        localField: 'company_id',
        foreignField: '_id',
        as: 'company'
        }},
      { $unwind: '$company' },
      { $project: {
    "first_name" : 1,
    "last_name" : 1,
    "job" : 1,
    "sector" : "$company.sector",
    "company" : "$company.name"
}}])