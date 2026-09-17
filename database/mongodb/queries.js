use metro_ride

db.vehicles.find({available: true, category: "SUV"})

db.vehicles.find(
  {serviceAreas: "Airport"},
  {vehicleId: 1, registration: 1, model: 1, available: 1}
)

db.reviews.find({rating: {$gte: 4}}).sort({rating: -1})

db.reviews.aggregate([
  {$group: {
    _id: "$driverId",
    averageRating: {$avg: "$rating"},
    reviewCount: {$sum: 1}
  }},
  {$sort: {averageRating: -1}},
  {$limit: 10}
])

db.reviews.updateOne(
  {reviewId: 1},
  {$set: {comment: "Updated review after customer feedback"}}
)

db.reviews.deleteOne({reviewId: 120})

db.vehicles.find({category: "SUV", available: true}).explain("executionStats")
